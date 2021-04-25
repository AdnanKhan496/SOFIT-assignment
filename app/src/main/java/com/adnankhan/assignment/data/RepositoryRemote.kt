package com.adnankhan.assignment.data

class RepositoryRemote(private val apiService: ApiService) {

    suspend fun getDrinksByName(name:String) = apiService.searchByName(name)
    suspend fun getDrinksByAlphabet(name:String) = apiService.searchByAlphabet(name)
}