import java.io.*;
import java.util.*;

public class KMeansMain {
    double[][] loadPoints(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            int n = sc.nextInt();
            int d = sc.nextInt();
            double[][] data = new double[n][d];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < d; ++ j) {
                    data[i][j] = sc.nextDouble();
                }
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("error while reading data points file");
        }
        return null;
    }
    
    int[] loadClusters(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            int n = sc.nextInt();
            int[] clusterID = new int[n];
            for (int i = 0; i < n; ++ i) {
                clusterID[i] = sc.nextInt();
            }
            return clusterID;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("error while reading cluster file");
        }
        return null;
    }
    
    void run(String[] args) {
    	// giving path in command
        if (args.length != 2) {
            System.out.println("[usage] <data-file> <ground-truth-file>");
            return;
        }
        
        String dataFilename = args[0];
        String groundtruthFilename = args[1];
      
        /*
         * giving path in the file
         * String base=System.getProperty("user.dir");
    	String dataFilename = base+"/data/self_test.data";
        String groundtruthFilename = base+"/data/self_test.ground";*/
        
        double[][] data = loadPoints(dataFilename);
        int[] groundtruth = loadClusters(groundtruthFilename);
        int ncenter=5;
        double[][] centers = new double[ncenter][data[0].length];
        for (int i = 0; i < ncenter; ++ i) {
            for (int j = 0; j < data[i].length; ++ j) {
                centers[i][j] = data[i][j];
            }
        }
        
        int[] result = KMeans.kmeans(data, centers, 100, 1e-6);
        
        System.out.println("Purity = " + SupervisedEvaluation.purity(groundtruth, result));
        System.out.println("NMI = " + SupervisedEvaluation.NMI(groundtruth, result));
    }
    
    public static void main(String[] args) {
        new KMeansMain().run(args);
    }
}
