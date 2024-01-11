package com.multi.weka;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

//의사결정나무(DecisionTree) 모델을 이용해서 Universal Bank 데이터셋을 학습해보자
//1. 데이터 로드-로드하여 훈련데이터셋과 검증데이터셋으로 분할한다
//2. 훈련과 검증데이터셋에서 정답 데이터 지정
//3. 교차검증 셋팅
//4. 알고리즘 모델을 생성해서 학습을 진행
//5. 결과 평가/검증
//6. 선택한 모델을 파일로 저장==> 후에 다시 모델을 로드해서 다시 학습을 진행
//7. 임의의 데이터로 모델을 적용해서 분류해본다
//8. decision tree를 시각화 해보자
public class Weka02DecisionTree {

	Instances data, train, test;
	//Classifier model;
	J48 model; //J48=>Decision Tree 모델
	
	String file1="C:\\Weka-3-9\\data\\UnivBank\\UniversalBank.arff";//학습데이터
	String file2="C:\\Weka-3-9\\data\\UnivBank\\UniversalBank_myData.arff";//실제 데이터(테스트용)
	
	Evaluation eval;
	
	public static void main(String[] args) {
		Weka02DecisionTree app=new Weka02DecisionTree();
		try {
			//1. 데이터 로드
			app.loadArff(app.file1);
			
			//2. 학습모델 생성
			app.generateModel();
			
			//3. 훈련데이터 교차검증 설정 후 학습 진행
			app.buildModel(10);
			
			//4. 테스트 데이터로 평가하기
			app.evalate();
			
			//5. 학습모델 결과 시각화
			app.treeViewInstances(app.data, app.model, app.eval);
			
			//6. 임의의 데이터에 대한 예측결과 보기
			app.testPredict(app.file2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//-----------------------------------

	//1. 데이터 로드
	private void loadArff(String file) throws Exception {
		DataSource ds=new DataSource(file);
		data=ds.getDataSet();
		data.randomize(new Random(1));
		
		//학습데이터와 검증데이터로 분리
		train=data.trainCV(10, 0, new Random(1));
		test=data.testCV(10, 0);
		
		//정답데이터 지정=>class assigner
		if(data.classIndex()==-1) {
			train.setClassIndex(train.numAttributes()-1);//마지막 속성을 target(label)으로 지정
			test.setClassIndex(test.numAttributes()-1);
		}
	}//----------------------------
	
	//2. 학습모델 생성
	private void generateModel() {
		model=new J48();
		model.setMinNumObj(10); //=> 설정값이 클수록 노드수가 줄면서 나무의 깊이도 낮아진다 => 가지치기 설정
		model.setUnpruned(false); //가지치기 실행 여부
		
	}//----------------------------------
	
	//3. 훈련데이터 교차검증 설정 후 학습 진행
	private void buildModel(int numfolds) throws Exception {
		eval=new Evaluation(train);
		eval.crossValidateModel(model, train, numfolds, new Random(1)); //교차검증 설정
		model.buildClassifier(train); //학습을 진행
		System.out.println("J48모델로 학습 완료!!");
	}//-----------------------------------
	
	//4. 테스트 데이터로 평가하기
	private void evalate() throws Exception {
		
		eval.evaluateModel(model, test);
		
		int total=(int)eval.numInstances();//분류 대상 데이터 수
		int accuracy=(int)eval.correct();//정분류 된 데이터 수
		int percent=accuracy*100/total; //정분류율(사용자에 의한)
		double percent2=eval.pctCorrect(); //정분류율
		System.out.println("---------결과-------------------------");
		System.out.printf("분류대상 데이터수 : %d개\n",total);
		System.out.printf("정분류건수 : %d개\n",accuracy);
		System.out.printf("정분류율1 : %d 퍼센트\n",percent);
		System.out.printf("정분류율2 : %.2f 퍼센트\n",percent2);
		System.out.println("-------summary-----------------------");
		System.out.println(eval.toSummaryString());
		System.out.println("-------------------------------------");
	}//------------------------------------
	
	//5. 학습모델 결과 시각화
	private void treeViewInstances(Instances data, J48 model, Evaluation eval) throws Exception {
		TreeVisualizer panel=new TreeVisualizer(null, model.graph(), new PlaceNode2());
		JFrame f=new JFrame("정분류율: "+String.format("%.2f", eval.pctCorrect()));
		java.awt.Container cp=f.getContentPane();
		cp.add(panel, "Center");
		
		JTextArea ta=new JTextArea(eval.toSummaryString());
		JScrollPane sp=new JScrollPane(ta);
		cp.add(sp, "South");
		ta.setFont(new java.awt.Font("sans-serif", java.awt.Font.BOLD,20));
		
		f.setSize(800,800);
		f.setVisible(true);
		
	}//-------------------------------------
	
	//6. 임의의 데이터에 대한 예측결과 보기
	private void testPredict(String file) throws Exception {
		DataSource ds=new DataSource(file);
		Instances predData=ds.getDataSet();
		
		int num=predData.numAttributes();//속성개수
		predData.setClassIndex(num-1);//정답지정
		
		int total=predData.numInstances();
		System.out.println("데이터 수: "+total);
		
		for(int i=0; i<total; i++) {
			System.out.println("-------Data "+i+"---------------------");
			Instance obj=predData.instance(i);
			double pred=model.classifyInstance(obj);
			System.out.println("pred: "+pred);
			
			String givenValue=predData.classAttribute().value((int)obj.classValue()); //파일에 적혀있는 class value
			System.out.println("givenValue: "+givenValue);
			System.out.println("predict value: "+predData.classAttribute().value((int) pred)); //예측한 class value
			
			System.out.println("******** 확 률 분 포 ******************");
			double[] prediction=model.distributionForInstance(obj);
			double prob1=prediction[0]; 
			double prob2=prediction[1];
			System.out.println("No일 확률: "+prob1);
			System.out.println("Yes일 확률: "+prob2);
		}//for-------------------------------------
		
	}//----------------------------------------


}//////////////////////////////////








