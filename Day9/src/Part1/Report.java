package Part1;

import java.util.ArrayList;
import java.util.Collections;

public class Report {
    private ArrayList<Long> report;

    public Report(String report) {
        this.report = new ArrayList<>();
        String[] splitReport = report.split(" ");
        for (String s : splitReport) {
            this.report.add(Long.parseLong(s));
        }
    }

    public ArrayList<Long> getReport() {
        return report;
    }

    public ArrayList<ArrayList<Long>> getSteps() {
        ArrayList<Long> currentReport = report;
        ArrayList<ArrayList<Long>> allSteps = new ArrayList<>();
        allSteps.add(this.getReport());

        while (!checkIfZero(currentReport)) {

            ArrayList<Long> currentSteps = new ArrayList<>();

            for (int i = 0; i <= currentReport.size() - 2; i++) {
                currentSteps.add(currentReport.get(i + 1) - currentReport.get(i));
            }
            allSteps.add(currentSteps);
            currentReport = currentSteps;
        }
        return allSteps;
    }

    public boolean checkIfZero(ArrayList<Long> input) {
        for (long num : input) {
            if (num != 0) return false;
        }
        return true;
    }

    public long getHistoryValue() {
        ArrayList<ArrayList<Long>> steps = getSteps();
        Collections.reverse(steps);

        long stepModifier = 0;
        for (ArrayList<Long> currentSteps : steps) {
            currentSteps.add(currentSteps.getLast() + stepModifier);
            stepModifier = currentSteps.getLast();
        }
        return steps.getLast().getLast();
    }
}
