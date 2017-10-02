package com.example.xuruihan.cats;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.xuruihan.cats.model.SignupManager;
import com.example.xuruihan.cats.model.User;
import com.example.xuruihan.cats.model.UserItem;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignUpFragment.OnSignupFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SignUpFragment extends Fragment {


    private OnSignupFragmentInteractionListener signupListener;

    private Button cancelButton;
    private Button registerButton;
    private EditText userText;
    private EditText passwordText;
    private RadioGroup radioGroup;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cancelButton = (Button) getActivity().findViewById(R.id.cancel_signup_button);
        registerButton = (Button) getActivity().findViewById(R.id.user_sign_up_button);
        userText = (EditText) getActivity().findViewById(R.id.userTextView);
        passwordText = (EditText) getActivity().findViewById(R.id.passwordeditText);
        radioGroup = (RadioGroup) getActivity().findViewById(R.id.radioGroup);
        cancelButton.setOnClickListener((View v) -> {
            signupListener.cancelSignup();
        });
        registerButton.setOnClickListener((View v) -> {
            String username = userText.getText().toString();
            String password = passwordText.getText().toString();

            if (username.matches("[\\S]+") && password.matches("[\\S]+") && radioGroup.getCheckedRadioButtonId() != -1) {
                boolean isAdmin;
                int selectedButtonId = radioGroup.getCheckedRadioButtonId();

                isAdmin = !(selectedButtonId == R.id.catButton);

                SignupManager.getInstance().doSignup(username, password, isAdmin, getActivity());


                signupListener.signupToMainPage();
                Toast.makeText(getActivity(), "Thanks for registering", Toast.LENGTH_SHORT).show();


            } else {
                if (!username.matches("[\\S]+") && password.matches("[\\S]+")) {
                    Toast.makeText(getActivity(), "Please type in a valid username", Toast.LENGTH_SHORT).show();
                } else if (username.matches("[\\S]+") && !password.matches("[\\S]+")) {
                    Toast.makeText(getActivity(), "Please type in a valid password", Toast.LENGTH_SHORT).show();
                } else if (!username.matches("[\\S]+") && !password.matches("[\\S]+")){
                    Toast.makeText(getActivity(), "Please type in a valid username and password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Please choose an account type", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignupFragmentInteractionListener) {
            signupListener = (OnSignupFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        signupListener = null;
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
    public interface OnSignupFragmentInteractionListener {
        // TODO: Update argument type and name
        void cancelSignup();
        void signupToMainPage();
    }
}
