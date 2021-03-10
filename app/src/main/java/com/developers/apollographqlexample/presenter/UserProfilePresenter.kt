package com.developers.apollographqlexample.presenter


import com.developers.apollographqlexample.contract.ContractInterface
import com.developers.apollographqlexample.model.UserDataViewModel

class UserProfilePresenter(_view: ContractInterface.View) : ContractInterface.Presenter {

    private var view: ContractInterface.View = _view
    private var model: ContractInterface.Model = UserDataViewModel()

    override fun networkCall() {
        model?.requestUserDataModel(this)
    }

    override fun showUserData() = model.getUserDataModel()

    override fun uiAutoUpdate() {
        view.updateViewData()
    }

    override fun processing() {
        view.showProgress()
    }

    override fun processingComplete() {
        view.hideProgress()
    }

    override fun processingError() {
        view.showErrorMessage()
    }


}