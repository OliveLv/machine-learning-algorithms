public class SupervisedEvaluation
{
    public static double purity(int[] groundtruthAssignment, int[] algorithmAssignment) {
        // TODO compute the purity
    	int n=algorithmAssignment.length;
    	int [][]count=new int[n][n];
    	int []maxs=new int [n];
		for (int i = 0; i < n; i++) {
			int idx = algorithmAssignment[i];

			int jdx = groundtruthAssignment[i];
			count[idx][jdx]++;
			if (maxs[idx] < count[idx][jdx])
				maxs[idx] = count[idx][jdx];

		}
    	int purity=0;
    	for(int i=0;i<n;i++)
    		purity+=maxs[i];
        return purity*1.0/n;
    }
    
    public static double NMI(int[] groundtruthAssignment, int[] algorithmAssignment) {
        // TODO compute the purity
    	int n=algorithmAssignment.length;
    	int [][]count=new int[n][n];
    	int []total1=new int[n];
    	int []total2=new int [n];
		for (int i = 0; i < n; i++) {
			int idx = algorithmAssignment[i];
			int jdx = groundtruthAssignment[i];
			total1[idx]++;
			total2[jdx]++;
			count[idx][jdx]++;
		}
		double nmi=0;
		double hc=0,tc=0;
		for(int i=0;i<n;i++){
		    if(total1[i]!=0)hc+=total1[i]*Math.log(total1[i]*1.0/n);	
		    if(total2[i]!=0)tc+=total2[i]*Math.log(total2[i]*1.0/n);	
			for(int j=0;j<n;j++){
				if(total1[i]!=0&&total2[j]!=0&&count[i][j]!=0)nmi+=count[i][j]*Math.log(count[i][j]*1.0*n/(total1[i]*total2[j]));
			}
		}
        return nmi/Math.sqrt(hc*tc);
    }
}
