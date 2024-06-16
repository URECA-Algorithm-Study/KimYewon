package b_2564;

import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int garo = sc.nextInt(); // 가로 길이 
		int sero = sc.nextInt(); // 세로 길이 
		int n_store = sc.nextInt(); // 상점 개수 
		
		// 가로 x 세로 바둑판식 배열 내 좌표를 저장할 2D 생성 
		int[][] arr = new int[2*(garo+sero)][2];
		
//		int[][] stores = new int[n_store][2]; // 상점별 위치를 저장하는 2D 배열
		int result = 0; // 최단 거리의 합 
		
		// 좌표 2차원 배열에 저장 (시계 방향 순서)
		for (int i=0; i<garo; i++) {
			arr[i][0] = 1;
			arr[i][1] = i;
		}
		for (int i=0; i<=sero; i++) {
			arr[garo+i][0] = 4;
			arr[garo+i][1] = i;
		}
		for (int i=1; i<=garo; i++) {
			arr[garo+sero+i][0] = 2;
			arr[garo+sero+i][1] = garo-i;
		}
		for (int i=1; i<=sero-1; i++) {
			arr[2*garo+sero+i][0] = 3;
			arr[2*garo+sero+i][1] = sero-i;
		}
		
		// 바둑판식 배열 출력 예시 (이후 주석 처리)
//		for (int i=0; i<arr.length; i++) {
//			System.out.println(arr[i][0]+" "+arr[i][1]);
//		}
		
		int[] clockwise = new int[n_store]; // 상점별 시계 방향 기준 인덱스 저장 
		int[] anticlockwise = new int[n_store]; // 상점별 반시계 방향 기준 인덱스 저장 
		
		for (int i=0; i<n_store; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int ClockwiseIndex = 0; // 시계 방향일 때 arr 내 {a,b}의 인덱스 
			int AnticlockwiseIndex = 0; // 반시계 방향일 때 arr 내 {a,b}의 인덱스
			
//			int[] tmp = new int[] {a,b};
			
			// arr 돌면서 {a,b}와 일치하는 배열 찾고 해당 인덱스 저장
			for (int j=0; j<arr.length; j++) {
				if (a==arr[j][0] && b==arr[j][1]) {
					ClockwiseIndex = j;
					AnticlockwiseIndex = arr.length - j;
				}
			}
			clockwise[i] = ClockwiseIndex;
			anticlockwise[i] = AnticlockwiseIndex;
		}
		
		// 동근이 위치 입력 받기
		int dong1 = sc.nextInt();
		int dong2 = sc.nextInt();
		int dong_cwIndex = 0; // 동근이의 시계 방향 기준 위치
		int dong_acwIndex = 0; // 동근이의 반시계 방향 기준 위치
		
		for (int i=0; i<arr.length; i++) { // 바둑판 내 동근이 위치를 시계 방향, 반시계 방향 기준으로 각각 인덱스 저장
			if (dong1 == arr[i][0] && dong2==arr[i][1]) {
				dong_cwIndex = i;
				dong_acwIndex = arr.length-i;
			}
		}
		
		// 시계 방향 기준 상점 - 동근 거리 : 절댓값(동근 위치 - 상점 위치)
		/* 반시계 방향 기준 상점 - 동근 거리
		 * - 시계 방향 기준 [1,0]으로부터 거리가 동근 > 상점일 때 : 반시계(동근 index) + 시계(상점 index)
		 * - 시계 방향 기준 [1,0]으로부터 거리가 상점 > 동근일 때 : 시계(동근 index) + 반시계(상점 index) 
		 */
		for (int i=0; i<clockwise.length; i++) {
			int cw_dong, acw_dong;
			if (dong_cwIndex > clockwise[i]) {
				cw_dong = clockwise[i] + dong_acwIndex;
				acw_dong = dong_cwIndex - clockwise[i];
			} else {
				cw_dong = clockwise[i] - dong_cwIndex;
				acw_dong = anticlockwise[i] + dong_cwIndex;
			}
			result += Math.min(cw_dong, acw_dong);
//			int cw_dong = Math.abs(clockwise[i]-dong_cwIndex); // 시계 방향으로 돌 때 상점 - 동근 절댓값을 임시 변수에 저
//			int acw_dong;
//			if ((clockwise[i] - dong_cwIndex)>0) {
//				acw_dong = dong_cwIndex + anticlockwise[i];
//			} else {
//				acw_dong = dong_acwIndex + clockwise[i];
//			}
//			
//			if (cw_dong < acw_dong) {
//				result += cw_dong;
//			} else {
//				result += acw_dong;
//			}
		}
		System.out.println(result);
		sc.close();
	}
	

}
