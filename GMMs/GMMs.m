%����2����ά��̬����
MU1    = [1 2];
SIGMA1 = [1 0; 0 0.5];
MU2    = [-1 -1];
SIGMA2 = [1 0; 0 1];
X      = [mvnrnd(MU1, SIGMA1, 1000);mvnrnd(MU2, SIGMA2, 1000)];

%��ʾ
%scatter(X(:,1),X(:,2),10,'.');

%GMMs ѧϰ,���Լ���ƴ���, �������Matlab�Դ���ѧϰ����
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%options = statset('Display','final');
%obj2 = gmdistribution.fit(X,2,'Options',options);
%��ʼ��mu sigma
mu=[mean(X(1:1000,:));mean(X(1001:end,:))];
sigma=cat(3,cov(X),cov(X));
%��ȡ������Ŀn��������Ŀm
[n,m]=size(X);
% k ��ʾ��˹ģ�͸���
k=2;
E=[];
oldmu=0;
iter=0;
MAX_Iter=1000;
while iter<MAX_Iter
    iter=iter+1;
    oldmu=mu;
    % ���� E
    for i=1:n
        for j=1:k
            E(i,j)=exp(-(X(i,:)-mu(j,:))*inv(sigma(:,:,j))*(X(i,:)-mu(j,:))'/2);
        end
    end
    E=E./repmat(sum(E,2),1,2);
    % ���� mu
    for i=1:k
        mu(i,:)=sum(repmat(E(:,i),1,2).*X,1)./sum(E(:,i));
    end
    % ���� sigma
    for j=1:k
        Cov=0;
        for i=1:n
            Cov=Cov+E(i,j).*(X(i,:)-mu(j,:))'*(X(i,:)-mu(j,:));
        end
        sigma(:,:,j)=Cov./sum(E(:,j));
    end
    % ����ֹͣ����
    if abs(det(mu-oldmu))<=eps
        %abs(det(mu-oldmu))
        break
    end
end
p = ones(1,2)/2;
obj = gmdistribution(mu,sigma,p);
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%��ʾѧϰ��ģ��
figure,h = ezmesh(@(x,y)pdf(obj,[x,y]),[-8 6], [-8 6]);

%��ʾѧϰ����
mu = obj.mu
sigma = obj.Sigma

