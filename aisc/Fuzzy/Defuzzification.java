public class Deffuzification {
    public static void main(String[] args) {
        double[] centre = {15, 35, 50};
        double[] height = {0.75, 1.0, 0.5};

        //(sum of center[i] * height[i]) / sum of height[i]
        double numerator = 0;
        double denominator = 0;

        for(int i = 0; i < centre.length; i++) {
            numerator += height[i]*centre[i];
            denominator += height[i];
        }
        double crisp_value = numerator/denominator;
        System.out.println(crisp_value);
    }
}

//we simply average the centres