from utils import * 
from math import exp 

def kernel(data, sigma):
    nData = len(data)
    Gram = [[0] * nData for i in range(nData)] 
    # TODO
    # Calculate the Gram matrix 
    nDim=len(data[0])
    for i in range(nData):
        for j in range(i,nData):
            val=0
            for k in range(nDim):
                val+=(data[i][k]-data[j][k])*(data[i][k]-data[j][k])
            Gram[i][j]=Gram[j][i]=exp(-val/sigma/sigma/2)
    return Gram 


