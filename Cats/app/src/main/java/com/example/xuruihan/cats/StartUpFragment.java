package com.example.xuruihan.cats;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 */
public class StartUpFragment extends Fragment {

    @Nullable
    private OnStartUpFragmentInteractionListener startUpListener;

    /**
     * Empty public constructor of Welcome page
     */
    public StartUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_startpage, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button loginButton = (Button) getActivity().findViewById(R.id.login_button);
        Button signUpButton = (Button) getActivity().findViewById(R.id.sign_up_button);

        loginButton.setOnClickListener((View v) -> {
            assert startUpListener != null;
            startUpListener.goToLogin();
        });

        signUpButton.setOnClickListener((View v) -> {
            assert startUpListener != null;
            startUpListener.goToSignup();
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStartUpFragmentInteractionListener) {
            startUpListener = (OnStartUpFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        startUpListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnStartUpFragmentInteractionListener {
        // TODO: Update argument type and name
        void goToLogin();
        void goToSignup();
    }
}
