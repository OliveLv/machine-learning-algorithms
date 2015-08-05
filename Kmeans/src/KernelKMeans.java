public class KernelKMeans
{
    public static double[][] kernel(double[][] data, double sigma) {
        // TODO transform the data points to kernel space
        // here we are going to implement RBF kernel, K(x_i, x_j) = e^{\frac{-|x_i - x_j|^2}{2 \sigma^2}}
    	int n=data.length;
    	int m=data[0].length;
    	double [][]newdata=new double[n][n];
    	for(int i=0;i<n;i++){
    		for(int j=i;j<n;j++){
    			double val=0;
    			for(int k=0;k<m;k++)
    				val+=Math.pow(data[i][k]-data[j][k], 2);
    			newdata[i][j]=newdata[j][i]=Math.exp(-val/sigma/sigma/2);
    		}
    	}
        return newdata;
    }
}
