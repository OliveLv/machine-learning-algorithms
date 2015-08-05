public class KMeans {
    public static double computeSSE(double[][] data, double[][] centers, int[] clusterID) {
        double sse = 0;
        int n = data.length;
        for (int i = 0; i < n; ++ i) {
            int c = clusterID[i];
            sse += Utils.squaredDistance(data[i], centers[c]);
        }
        return sse;
    }
    
    public static int[] updateClusterID(double[][] data, double[][] centers) {
        int[] clusterID = new int[data.length];
        // TODO assign the closet center to each data point
        // you can use the function Utils.squaredDistance
        int n=data.length;
        int k=centers.length;
        for(int i=0;i<n;i++){
        	double min=Double.MAX_EXPONENT;
        	int min_idx=0;
        	for(int j=0;j<k;j++){
        		double dist=Utils.squaredDistance(data[i], centers[j]);
        		if(min>dist){
        			min=dist;
        			min_idx=j;
        		}
        	}
        	clusterID[i]=min_idx;
        }
        return clusterID;
    }
    
    public static double[][] updateCenters(double[][] data, int[] clusterID, int K) {
        double[][] centers = new double[K][data[0].length];
        // TODO recompute the centers based on current clustering assignment
        // If a cluster doesn't have any data points, in this homework, leave it to ALL 0s.
        int nums[]=new int[K];
        int n=data[0].length;
        for(int i=0;i<data.length;i++){
        	int idx=clusterID[i];
        	for(int j=0;j<n;j++){
        		centers[idx][j]+=data[i][j];
        	}
        	nums[idx]++;
        }
        for(int i=0;i<K;i++)
        	if(nums[i]!=0){
        		int num=nums[i];
        		for(int j=0;j<n;j++)
        			centers[i][j]/=num;
        	}
        return centers;
    }
    
    /** run kmeans a single time, with specific initialization and number of iterations
     *  double[][] data are the points need to be clustered
     *  double[][] centers are the initializations
     *  int maxIter is the max number of itertions for kmeans
     *  double tol is the tolerance for stop criterion
     *  return clusterID
    **/
    public static int[] kmeans(double[][] data, double[][] centers, int maxIter, double tol) {
        int n = data.length; // the number of data points
        if (n == 0) {
            return new int[0];
        }
        int k = centers.length;
        int[] clusterID = new int[n];
        if (k >= n) {
            for (int i = 0; i < n; ++ i) {
                clusterID[i] = i;
            }
            return clusterID;
        }

        double lastDistance = 1e100; // set to infinity
        for (int iter = 0; iter < maxIter; ++ iter) {
            clusterID = updateClusterID(data, centers);
            centers = updateCenters(data, clusterID, k);
            double distance = computeSSE(data, centers, clusterID);
            
            if ((lastDistance - distance) < tol || (lastDistance - distance) / lastDistance < tol) { // stop criterion
                System.out.println("# iterations = " + iter);
                System.out.println("SSE = " + distance);
                return clusterID;
            }
            lastDistance = distance;
        }
        System.out.println("# iterations = " + maxIter);
        System.out.println("SSE = " + lastDistance);
        return clusterID;
    }
}
