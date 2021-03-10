package com.developers.apollographqlexample.contract

import com.developers.apollographqlexample.presenter.UserProfilePresenter

interface ContractInterface {

    interface View {
        fun updateViewData()
        fun showProgress()
        fun hideProgress()
        fun showErrorMessage()
    }

    interface Presenter {
        fun networkCall()
        fun showUserData(): UserDetailQuery.Data?
        fun uiAutoUpdate()
        fun processing()
        fun processingComplete()
        fun processingError()
    }

    interface Model {
        fun getUserDataModel(): UserDetailQuery.Data?
        fun requestUserDataModel(userprofile : UserProfilePresenter)
    }

}