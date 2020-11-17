package com.electro.bikeapp.dtos

/**
 * @author jbyerline
 * @creationDate 09/30/2020
 * This DTO is the java representation of what the JSON request body parameters sent by the frontend will become
 */
class SearchInventoryDTO {

    String bikeColor

    BigDecimal priceMin

    BigDecimal priceMax

    Boolean inStock

    String condition

    String make

}
