package com.example.xuruihan.cats;

/**
 * Created by Victor on 10/21/2017.
 */

@SuppressWarnings("ALL")
public interface LoadingView {

    /**
     * Setter for uploading view
     */
    void setUpLoadingView();

    /**
     * Setter for download view
     * @param object
     */
    void displayResult(Object object);
}
