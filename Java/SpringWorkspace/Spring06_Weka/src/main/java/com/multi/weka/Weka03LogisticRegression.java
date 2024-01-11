package com.multi.weka;

import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

//1. 데이터 로드-로드하여 훈련데이터셋과 검증데이터셋으로 분할한다
//2. 훈련과 검증데이터셋에서 정답 데이터 지정
//3. 교차검증 셋팅
//4. 알고리즘 모델을 생성해서 학습을 진행
//5. 결과 평가/검증
//6. 선택한 모델을 파일로 저장==> 후에 다시 모델을 로드해서 다시 학습을 진행
//7. 임의의 데이터로 모델을 적용해서 분류해본다
public class Weka03LogisticRegression {
	
	String file1="C:\\Weka-3-9\\data\\UnivBank\\UniversalBank.arff";
	String file2="C:\\Weka-3-9\\data\\UnivBank\\UniversalBank_myData.arff";
	
	Logistic model;
	Evaluation eval;
	
	Instances data, train, test;
	
	public static void main(String[] args) throws Exception {
		Weka03LogisticRegression app=new Weka03LogisticRegression();
		app.loadArff(app.file1);
		app.generateModel();
		app.buildModel(10);
		app.evaluate();
		
		//학습모델을 파일로 저장하기
		app.saveModel("UniversalBank.model");
		//저장했던 학습모델을 복원시켜서, 임의의 데이터를 분류해보자
		app.testPredict();
		
	}//main()---------------------

	public void loadArff(String file) throws Exception {
		DataSource ds=new DataSource(file);
		data=ds.getDataSet();//전체 데이터 얻어오기
		//로지스틱회귀는 데이터를 정규화해야 한다 ==> weka에서는 필터(Normalize)로 제공한다.
		Normalize normalize=new Normalize();
		//정규화 필터를 전체 data에 적용하자
		normalize.setInputFormat(data);
		Instances newData=Filter.useFilter(data, normalize);
		//newData가 정규화된 데이터
		
		train=newData.trainCV(10, 0, new Random(1));
		test=newData.testCV(10, 0);
		
		train.setClassIndex(train.numAttributes()-1);
		test.setClassIndex(test.numAttributes()-1);
		
	}//-------------------------------
	
	private void generateModel() throws Exception {
		model=new Logistic();
		eval=new Evaluation(train);
	}//----------------------------------
	
	private void buildModel(int numfolds) throws Exception{
		//교차검증 설정 후 학습 진행
		eval.crossValidateModel(model, train, numfolds, new Random(1));
		model.buildClassifier(train);
	}//------------------------------
	
	private void evaluate() throws Exception{
		eval.evaluateModel(model, test);
		System.out.println("-------------------------------");
		System.out.printf("전체 데이터 건수: %d개\n",(int)eval.numInstances());
		System.out.printf("정분류 건수: %d개\n",(int)eval.correct());
		System.out.printf("오분류 건수: %d개\n",(int)eval.incorrect());
		System.out.printf("정분류율: %.2f percent \n",eval.pctCorrect());
	}//-------------------------------
	
	public void saveModel(String file) {
		String path="C:\\Weka-3-9\\data\\UnivBank\\"+file;
		
		System.out.println("학습 모델을 "+path+"에 저장합니다");
		try {
			SerializationHelper.write(path, model);
			System.out.println("저장 완료!!");
		} catch (Exception e) {
			e.setStackTrace(null);
		}
	}//---------------------------------------
	
	public Classifier loadModel (String file) {
		try {
			Classifier model2=(Classifier)SerializationHelper.read(file);
			return model2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}//-----------------------------------
	
	private void testPredict() throws Exception {
		String file="UniversalBank.model";
		String path="C:\\Weka-3-9\\data\\UnivBank\\"+file;
		Classifier model2=loadModel(path);
		//저장했던 학습 모델 복원
		DataSource ds=new DataSource(file2);
		Instances predData=ds.getDataSet();
		predData.setClassIndex(predData.numAttributes()-1);
		System.out.println("실제 데이터 수: "+predData.numInstances()+"개");
		
		//실제 데이터도 정규화 해야 함
		Normalize norm=new Normalize();
		norm.setInputFormat(predData);
		Instances newData=Filter.useFilter(predData, norm);
		//복원한 학습모델에 실제 데이터를 넣어 분류해보자
		Logistic lmodel=null;
		if(model2!=null && model2 instanceof Logistic) {
			lmodel=(Logistic)model2;
		}
		
		for(int i=0; i<newData.numInstances(); i++) {
			Instance obj=newData.instance(i);
			double pred=lmodel.classifyInstance(obj);
			System.out.println("pred: "+pred);
			int k=(int)obj.classValue();
			System.out.println("실제 데이터값 : " +newData.classAttribute().value(k));
			System.out.println("예측 데이터값 : " +newData.classAttribute().value((int)pred));
			
			System.out.println("*********** 확 률 분 포 ******************");
			double[] prob=lmodel.distributionForInstance(obj);
			System.out.println("No: prob[0] : "+prob[0]);
			System.out.println("Yes: prob[1] : "+prob[1]);
		}
		
	}//-------------------------------------

}////////////////////////////////////////////////










