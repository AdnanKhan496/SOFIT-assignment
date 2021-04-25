package com.adnankhan.assignment.data

import android.content.Context
import com.adnankhan.assignment.R
import com.adnankhan.assignment.data.local.RepositoryLocal
import com.adnankhan.assignment.data.model.Drinks
import com.adnankhan.assignment.data.model.ResponseModel
import com.adnankhan.assignment.utils.Constants.ERROR_INTERNET
import retrofit2.Response
import java.lang.Exception

class DataLayer(
    private val repositoryRemote: RepositoryRemote,
    private val repositoryLocal: RepositoryLocal,
    private val context: Context
) {

    suspend fun getDrinksByName(name: String): Resource<ResponseModel?> {
        return try {
            val response = repositoryRemote.getDrinksByName(name)
            processResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(ERROR_INTERNET)
        }

    }

    suspend fun getDrinksByAlphabet(name: String): Resource<ResponseModel?> {
        return try {
            val response = repositoryRemote.getDrinksByAlphabet(name)
            processResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(ERROR_INTERNET)
        }
    }

    suspend fun getFavoriteDrinkById(id: String): Drinks? {
        return repositoryLocal.getFavoriteDrinkById(id)
    }

    suspend fun updateDrink(drinks: Drinks) {
        repositoryLocal.update(drinks)
    }

    suspend fun saveAllData(list: List<Drinks>) {
        repositoryLocal.insertAll(list)
    }

    suspend fun fetchAllData(): List<Drinks> {
        return repositoryLocal.getAll()
    }

    suspend fun getAllFavoriteDrink(): List<Drinks> {
        return repositoryLocal.getAllFavoriteDrinks()
    }

    suspend fun delete() {
        repositoryLocal.delete()
    }

    private fun <T> processResponse(response: Response<T>): Resource<T?> {
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(
                context.resources.getString(R.string.apiErrorMessage),
                statusCode = response.code()
            )
        }
    }
}