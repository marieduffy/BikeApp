package com.electro.bikeapp.services

import com.electro.bikeapp.domains.BikeDomain
import com.electro.bikeapp.dtos.AddProductDTO
import com.electro.bikeapp.dtos.SearchInventoryDTO
import com.electro.bikeapp.repositories.InventoryRepository
import com.electro.bikeapp.utils.ByteToHexString
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

@SuppressWarnings([''])
@Slf4j
@Service
class InventoryService {

    // The repository must be autowired. The repository is the class that queried the database
    @Autowired
    InventoryRepository bikeInventoryRepository

    ByteToHexString converter = new ByteToHexString()

    // This method returns a list of bikes with the given search parameters
    List<BikeDomain> searchInventory (SearchInventoryDTO searchParams) {
        // We call the bikeInventoryRepository and return it's result
        List<BikeDomain> searchedBikeList = bikeInventoryRepository.allParamSearch(
                searchParams.bikeColor,
                searchParams.priceMin,
                searchParams.priceMax,
                searchParams.inStock,
                searchParams.condition,
                searchParams.make
        )

        return searchedBikeList
    }

    /**
     * Add bike to inventory
     * @param AddProductDTO[]
     * @return void
     */
    void addProduct (AddProductDTO[] newProductParametersArray) {
        // Loop through JSON array of new bikes
        for (int i = 0; i < newProductParametersArray.size(); i++) {
            // For each bike:

            // Make string of all bike data
            String bikeData = newProductParametersArray[i].bikeDisplayName +
                    newProductParametersArray[i].color +
                    newProductParametersArray[i].make +
                    newProductParametersArray[i].model +
                    newProductParametersArray[i].retailCost.toString() +
                    newProductParametersArray[i].wholesaleCost.toString() +
                    newProductParametersArray[i].description +
                    newProductParametersArray[i].condition

            // Hash bike data
            MessageDigest digest = MessageDigest.getInstance('SHA-256')
            byte[] hash = digest.digest(bikeData.getBytes(StandardCharsets.UTF_8))

            // Convert hash to hex
            String hexHash = converter.toHexString(hash)

            // Look if that hash exists in DB
            Optional<BikeDomain> existingBike = bikeInventoryRepository.findByHashValue(hexHash)

            // If bike exists in DB, simply update quantity
            if (existingBike.isPresent()) {
                existingBike.get().quantity = existingBike.get().quantity + newProductParametersArray[i].quantity
                bikeInventoryRepository.save(existingBike.get())
            }

            // Else, make a new bike entry
            else {
                BikeDomain bike = new BikeDomain()
                // Set in all the parameters
                bike.bikeDisplayName = newProductParametersArray[i].bikeDisplayName
                bike.color = newProductParametersArray[i].color
                bike.make = newProductParametersArray[i].make
                bike.model = newProductParametersArray[i].model
                bike.retailCost = newProductParametersArray[i].retailCost
                bike.wholesaleCost = newProductParametersArray[i].wholesaleCost
                bike.description = newProductParametersArray[i].description
                bike.addedBy = newProductParametersArray[i].addedBy
                bike.condition = newProductParametersArray[i].condition
                bike.quantity = newProductParametersArray[i].quantity
                bike.inStock = true
                bike.hashValue = hexHash
                // Save the bike to the database
                bikeInventoryRepository.save(bike)
            }
        }
    }

    /**
     * Remove bike from inventory
     * @param AddProductDTO[]
     * @return void
     */
    void removeProduct (String[] productHash) {
        // Loop through String array of hashs
        for (int i = 0; i < productHash.size(); i++) {
            // For each bike:

            // Look if that hash exists in DB
            Optional<BikeDomain> bike = bikeInventoryRepository.findByHashValue(productHash)

            // If bike exists in DB, decrement quantity
            if (bike.isPresent()) {
                bike.get().quantity = bike.get().quantity - 1
                bikeInventoryRepository.save(bike.get())
            }

            // Else, make a new bike entry
            else {
                log.error('Bike not found')
            }
        }
    }

}
