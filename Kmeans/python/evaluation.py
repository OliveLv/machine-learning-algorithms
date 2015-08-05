from math import log, sqrt
def  purity(groundtruthAssignment, algorithmAssignment):

    purity = 0
    # TODO  
    # Compute the purity 
    n=len(algorithmAssignment)
    count=[[0]*n for i in range(n)]
    maxs=[0]*n
    for i in range(n):
        idx=algorithmAssignment[i]
        jdx=groundtruthAssignment[i]
        count[idx][jdx]+=1
        if maxs[idx]<count[idx][jdx]:
            maxs[idx]=count[idx][jdx]
    for i in range(n):
        purity+=maxs[i]
    return purity*1.0/n  

def NMI(groundtruthAssignment, algorithmAssignment):

    NMI = 0
    # TODO
    # Compute the NMI
    n=len(algorithmAssignment)
    count=[[0]*n for i in range(n)]
    total1=[0]*n
    total2=[0]*n
    for i in range(n):
        idx=algorithmAssignment[i]
        jdx=groundtruthAssignment[i]
        total1[idx]+=1
        total2[jdx]+=1
        count[idx][jdx]+=1
    HC=0
    TC=0
    for i in range(n):
        if total1[i]!=0:
            HC+=total1[i]*log(total1[i]*1.0/n)
        if total2[i]!=0:
            TC+=total2[i]*log(total2[i]*1.0/n)
        for j in range(n):
            if total1[i]!=0 and total2[j]!=0 and count[i][j]!=0:
                NMI+=count[i][j]*log(count[i][j]*1.0*n/(total1[i]*total2[j]))
    return NMI/sqrt(HC*TC)
