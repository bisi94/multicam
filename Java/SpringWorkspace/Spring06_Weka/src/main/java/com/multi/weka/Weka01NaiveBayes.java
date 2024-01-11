package com.multi.weka;

import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

//NaiveBayes알고리즘을 이용해서 iris데이터를 분류하는 학습을 해보자. (다중 분류)
//1. 데이터 로드-로드하여 훈련데이터셋과 검증데이터셋으로 분할한다
//2. 훈련과 검증데이터셋에서 정답 데이터 지정
//3. 교차검증 셋팅
//4. 알고리즘 모델을 생성해서 학습을 진행
//5. 결과 평가/검증
//6. 선택한 모델을 파일로 저장==> 후에 다시 모델을 로드해서 다시 학습을 진행
//7. 임의의 데이터로 모델을 적용해서 분류해본다
public class Weka01NaiveBayes {
	
	private Instances irisData, train, test;
	private NaiveBayes model; //학습모델
	private Evaluation eval;

	public static void main(String[] args) {
		String path="C:/Weka-3-9/data/iris.arff"; //훈련 및 검증에 사용
		String path_test="C:/Weka-3-9/data/iris_test.arff"; //학습된 모델을 이용해 임의의 데이터를 분류해본다 
		
		Weka01NaiveBayes app=new Weka01NaiveBayes();
		app.loadArff(path);
		app.generateModel();
		app.buildModel(10); //교차검증수 10개
		app.evaluate(10);
		app.testClassify(path_test);
	}//main()-------------------------------------

	private void loadArff(String path) {
		try {
			DataSource ds=new DataSource(path);
			irisData=ds.getDataSet();//전체 데이타
			irisData.randomize(new Random(1)); //seed값 1지정
			//학습데이터와 검증데이터로 분류
			train=irisData.trainCV(10, 0, new Random(1));//학습데이터
			test=irisData.testCV(10,0);
			
			if(irisData.classIndex()==-1) {//정답데이터가 마지막에 있다면
				train.setClassIndex(train.numAttributes()-1);//마지막 속성을 정답 데이터로 지정
				test.setClassIndex(test.numAttributes()-1);
			}
			System.out.println("1. 데이터 준비 완료");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//------------------------------------
	
	// 2. 모델 생성
	private void generateModel() {
		model=new NaiveBayes();
		System.out.println("2. 나이브 베이즈 모델 생성 완료");
	}//---------------------------------
	
	// 3. 훈련데이터 교차검증 셋팅후 학습하기
	private void buildModel(int numfolds) {
		try {
			eval=new Evaluation(train);
			eval.crossValidateModel(model, train, numfolds, new Random(1));//교차검증
			//모델을 이용해 학습시키자
			model.buildClassifier(train); //train데이터로 학습하기
			System.out.println("3. 학습완료!!");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}//----------------------------------
	//4. test데이터로 평가하기
	private void evaluate(int numfolds) {
		try {
			eval.evaluateModel(model, test);
			System.out.println("4. 평가완료, 평가결과 요약--------------------------------------");
			System.out.printf("정분류율: %.2f\n", eval.pctCorrect());
			System.out.println("--------------------------------------------------------");
			String summary=eval.toSummaryString();
			System.out.println(summary);
			System.out.println("--------------------------------------------------------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//----------------------------------------------------
	//5. 임의의 데이터를 학습 모델이 잘 분류하는지 예측
	private void testClassify(String path_test) {
		try {
			DataSource ds=new DataSource(path_test);
			Instances predictData=ds.getDataSet();
			predictData.setClassIndex(predictData.numAttributes()-1);
			System.out.println("5. 임의의 데이터로 분류 예측");
			System.out.println("임의 데이터 개수: "+predictData.numInstances()+"개");
			int rows=predictData.numInstances();
			for(int i=0; i<rows; i++) {
				System.out.println("------------Data"+i+"-------------------------");
				Instance instance=predictData.instance(i);
				double pred=model.classifyInstance(instance);//예측값
				System.out.println("pred(예측한 분류값): "+pred);
				System.out.println("predicted value: "+predictData.classAttribute().value((int)pred));
			}//for---------------------------------------
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//-----------------------------------
}//////////////////////////////////////////////////








