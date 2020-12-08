package com.electro.bikeapp.services

import com.electro.bikeapp.domains.BikeDomain
import com.electro.bikeapp.dtos.SearchInventoryDTO
import com.electro.bikeapp.repositories.InventoryRepository
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class InventoryServiceSpec extends Specification {

    @Autowired
    InventoryService inventoryService

    @Autowired
    InventoryRepository inventoryRepository

    void searchAnItemInInventory() {
        given: 'A bike item'
        SearchInventoryDTO searchParams = new SearchInventoryDTO(
            bikeColor: "red",
            priceMin: 10000,
            priceMax: 10000000,
            inStock: "true",
            condition: "used",
            make: "Trek",
        )

        // Add bike to inventory
        BikeDomain e = new BikeDomain(
                bikeDisplayName: "bike3",
                addedBy: "Lili",
                make: "Trek",
                model: "fast",
                color: "red",
                wholesaleCost: 30000.00,
                retailCost: 500000.0,
                description: "A bad ass bike",
                inStock: "true",
                condition: "used",
                quantity: 8,
                hashValue: "wy4567b"
        )
        inventoryRepository.save(e)

        when: "An item is found"
        List<BikeDomain> list = inventoryService.searchInventory(searchParams)

        then: "Verify the search parameters match passed list"
        assert list.get(0).color == searchParams.bikeColor
        assert list.get(0).retailCost >= searchParams.priceMin
        assert list.get(0).retailCost <= searchParams.priceMax
        assert list.get(0).inStock == searchParams.inStock
        assert list.get(0).condition == searchParams.condition
        assert list.get(0).make == searchParams.make

        cleanup:
        inventoryRepository.delete(e)
    }

    void searchANonexistentItemInInventory() {
        given: 'A nonexisting bike item'
        SearchInventoryDTO searchParams = new SearchInventoryDTO(
                bikeColor: "blue",
                priceMin: 10000,
                priceMax: 10000000,
                inStock: "true",
                condition: "new",
                make: "Trek",
        )

        when: "Search inventory service is called"
        List<BikeDomain> list = inventoryService.searchInventory(searchParams)

        then: "Verify error thrown"
        def e = thrown(NotFoundException)
        assert e.message == 'Bike with ' + searchParams.bikeColor + ' and ' + searchParams.priceMin + ' and ' + searchParams.priceMax + ' and ' + searchParams.inStock + ' and ' + searchParams.condition + ' and ' + searchParams.make + 'does not exist '
    }
}
