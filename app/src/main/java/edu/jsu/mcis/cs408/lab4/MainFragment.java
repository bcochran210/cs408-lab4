package edu.jsu.mcis.cs408.lab4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
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
        double tip = Double.parseDouble("0." + tipPercentage.getText().toString());
        int bill = Integer.parseInt(billTotal.getText().toString());
        int diners = Integer.parseInt(numDiners.getText().toString());
        double newTotal = bill + (bill * tip);
        double totalPerDiner = newTotal / diners;
        double roundedTotal = Math.ceil(totalPerDiner * 100.0) / 100.0;

        result.setText("$" + roundedTotal);
    }

}
