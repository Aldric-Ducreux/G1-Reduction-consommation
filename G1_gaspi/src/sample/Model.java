package sample;

public class Model {
    private int val1;
    private int val2;

    public Model(int val1, int val2){
        this.val1 = val1;
        this.val2 = val2;
    }

    public double comp (char operation){
        double result=0;
        switch (operation) {
            case '+':
                result = val1 + val2; break;
        } return result;
        }
    }
}