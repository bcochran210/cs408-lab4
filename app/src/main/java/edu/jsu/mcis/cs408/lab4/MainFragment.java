package edu.jsu.mcis.cs408.lab4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    private TextView result;
    private EditText billTotal;
    private EditText tipPercentage;
    private EditText numDiners;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        result = (TextView)view.findViewById(R.id.totalDue);
        billTotal = (EditText)view.findViewById(R.id.billTotal);
        tipPercentage = (EditText)view.findViewById(R.id.tipPercentage);
        numDiners = (EditText)view.findViewById(R.id.numPeople);
        view.findViewById(R.id.calButton).setOnClickListener(this::onClick);
    }

    public void onClick(View view) {

        double tip = 0.0;
        double bill = 0.0;
        int diners = 0;

        Toast emptyTip = Toast.makeText(getActivity(), "Tip left blank.", Toast.LENGTH_SHORT);
        Toast emptyBill = Toast.makeText(getActivity(), "Bill left blank.", Toast.LENGTH_SHORT);
        Toast emptyDiners = Toast.makeText(getActivity(), "Diners left blank.", Toast.LENGTH_SHORT);

        if (tipPercentage.getText().toString() == "" || tipPercentage.getText().toString().isEmpty()) {
            emptyTip.show();
        } else {
            tip = Double.parseDouble(tipPercentage.getText().toString()) / 100;
        }

        if (billTotal.getText().toString() == "" || billTotal.getText().toString().isEmpty()) {
            emptyBill.show();
        } else {
            bill = Integer.parseInt(billTotal.getText().toString());
        }

        if (numDiners.getText().toString() == "" || numDiners.getText().toString().isEmpty()) {
            emptyDiners.show();
        } else {
            diners = Integer.parseInt(numDiners.getText().toString());
        }

        if (tip == 0.0 || bill == 0.0 || diners == 0.0) {
            Toast errorMsg = Toast.makeText(getActivity(), "0 is not a valid input.", Toast.LENGTH_SHORT);
            errorMsg.show();
        }

        double newTotal = bill + (bill * tip);
        double totalPerDiner = newTotal / diners;
        double roundedTotal = Math.ceil(totalPerDiner * 100.0) / 100.0;

        result.setText("$" + roundedTotal);
    }

}
