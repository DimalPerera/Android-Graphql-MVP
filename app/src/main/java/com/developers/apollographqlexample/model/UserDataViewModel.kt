package com.developers.apollographqlexample.model

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.developers.apollographqlexample.contract.ContractInterface
import com.developers.apollographqlexample.network.ApiEndPoint
import com.developers.apollographqlexample.presenter.UserProfilePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDataViewModel : ContractInterface.Model {

    var userData: UserDetailQuery.Data? = null;
    private lateinit var client: ApolloClient

    override fun getUserDataModel(): UserDetailQuery.Data? = userData

    override fun requestUserDataModel(userprofile: UserProfilePresenter) {

        GlobalScope.launch(Dispatchers.Main) {
            try {

                userprofile.processing()

                client = ApiEndPoint().setupApollo()

                client.query(UserDetailQuery
                        .builder()
                        .owner("JakeWharton")
                        .build())
                        .enqueue(object : ApolloCall.Callback<UserDetailQuery.Data>() {

                            override fun onFailure(e: ApolloException) {
                                println("onFailure " + e.message.toString())
                                userprofile.processingError()
                            }

                            override fun onResponse(response: Response<UserDetailQuery.Data>) {
                                println("onResponse " + response.data?.user()?.name())
                                userData = response.data!!;
                                userprofile.uiAutoUpdate()
                                userprofile.processingComplete()
                            }

                        })

            } catch (e: ApolloException) {
                userprofile.processingError()
            } catch (e: NullPointerException) {
                userprofile.processingError()
            }
        }


    }

}