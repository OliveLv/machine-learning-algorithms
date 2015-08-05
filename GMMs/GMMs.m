%产生2个二维正态数据
MU1    = [1 2];
SIGMA1 = [1 0; 0 0.5];
MU2    = [-1 -1];
SIGMA2 = [1 0; 0 1];
X      = [mvnrnd(MU1, SIGMA1, 1000);mvnrnd(MU2, SIGMA2, 1000)];

%显示
%scatter(X(:,1),X(:,2),10,'.');

%GMMs 学习,请自己设计代码, 替代以下Matlab自带的学习函数
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%options = statset('Display','final');
%obj2 = gmdistribution.fit(X,2,'Options',options);
%初始化mu sigma
mu=[mean(X(1:1000,:));mean(X(1001:end,:))];
sigma=cat(3,cov(X),cov(X));
%获取样本数目n和特征数目m
[n,m]=size(X);
% k 表示高斯模型个数
k=2;
E=[];
oldmu=0;
iter=0;
MAX_Iter=1000;
while iter<MAX_Iter
    iter=iter+1;
    oldmu=mu;
    % 更新 E
    for i=1:n
        for j=1:k
            E(i,j)=exp(-(X(i,:)-mu(j,:))*inv(sigma(:,:,j))*(X(i,:)-mu(j,:))'/2);
        end
    end
    E=E./repmat(sum(E,2),1,2);
    % 更新 mu
    for i=1:k
        mu(i,:)=sum(repmat(E(:,i),1,2).*X,1)./sum(E(:,i));
    end
    % 更新 sigma
    for j=1:k
        Cov=0;
        for i=1:n
            Cov=Cov+E(i,j).*(X(i,:)-mu(j,:))'*(X(i,:)-mu(j,:));
        end
        sigma(:,:,j)=Cov./sum(E(:,j));
    end
    % 给出停止条件
    if abs(det(mu-oldmu))<=eps
        %abs(det(mu-oldmu))
        break
    end
end
p = ones(1,2)/2;
obj = gmdistribution(mu,sigma,p);
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%显示学习到模型
figure,h = ezmesh(@(x,y)pdf(obj,[x,y]),[-8 6], [-8 6]);

%显示学习参数
mu = obj.mu
sigma = obj.Sigma

