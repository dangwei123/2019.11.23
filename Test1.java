给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
示例:

输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
class Solution {
    public int trap(int[] height) {
        int res=0;
        int[] maxLeft=new int[height.length];
        int[] maxRight=new int[height.length];
        for(int i=1;i<height.length-1;i++){
            maxLeft[i]=Math.max(maxLeft[i-1],height[i-1]);
        }
        for(int i=height.length-2;i>0;i--){
            maxRight[i]=Math.max(maxRight[i+1],height[i+1]);
        }
        for(int i=1;i<height.length-1;i++){
            int tmp=Math.min(maxLeft[i],maxRight[i]);
            if(tmp>height[i]){
                res+=tmp-height[i];
            }
        }
        return res;
    }
}

给定一个整数数组 A ，考虑 A 的所有非空子序列。

对于任意序列 S ，设 S 的宽度是 S 的最大元素和最小元素的差。

返回 A 的所有子序列的宽度之和。

由于答案可能非常大，请返回答案模 10^9+7。

 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sum-of-subsequence-widths
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int sumSubseqWidths(int[] A) {
        Arrays.sort(A);
        int mod=1000000007;
        int N=A.length;
        long res=0;
        long p=1;
        for(int i=0;i<N;i++){
            res=(res+(A[i]-A[N-i-1])*p)%mod;
            p=(p<<1)%mod;
        }
        return (int)res;
    }
}

假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的
地块上，它们会争夺水源，两者都会死去。

给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。
能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/can-place-flowers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int[] res=new int[flowerbed.length+2];
        for(int i=0;i<flowerbed.length;i++){
            res[i+1]=flowerbed[i];
        }
        for(int i=1;i<res.length-1;i++){
            if(res[i-1]==0&&res[i]==0&&res[i+1]==0){
                res[i]=1;
                n--;
            }
        }
        return n<=0;
    }
}

