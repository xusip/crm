import cn.edu.nncat.crm.settings.domain.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-17 16:13
 * @version:1.0
 **/
public class test {
   



    //冒泡排序
    //它重复地走访过要排序的元素列，依次比较两个相邻的元素，如果顺序（如从大到小、首字母从Z到A）错误就把他们交换过来。
    // 走访元素的工作是重复地进行，直到没有相邻元素需要交换，也就是说该元素列已经排序完成。
   /* public static void bubbleSort(int arr[]) {

        for(int i =0 ; i<arr.length-1 ; i++) {

            for(int j=0 ; j<arr.length-1-i ; j++) {

                if(arr[j]>arr[j+1]) {
                    int temp = arr[j];

                    arr[j]=arr[j+1];

                    arr[j+1]=temp;
                }
            }

        }
        System.out.println(Arrays.toString(arr));
    }*/

        //选择排序
        //首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，
        // 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
        /*public static void selectionSort(int[] arr){
            for(int i = 0; i < arr.length - 1; i++){
                // 交换次数
                // 先假设每次循环时，最小数的索引为i
                int minIndex = i;// 每一个元素都和剩下的未排序的元素比较
                for(int j = i + 1; j < arr.length; j++){
                    if(arr[j] < arr[minIndex]){//寻找最小数
                        minIndex = j;//将最小数的索引保存
                    }
                }//经过一轮循环，就可以找出第一个最小值的索引，然后把最小值放到i的位置

                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;

            }
            System.out.println(Arrays.toString(arr));
        }*/
       
        //插入排序
        //插入排序（Straight Insertion Sort）是一种最简单的排序方法，其基本操作是将一条记录插入到已排好的有序表中，
        // 从而得到一个新的、记录数量增1的有序表。
        /*public static void insertSort(int[] array){
            for(int i=1;i<array.length;i++)//第0位独自作为有序数列，从第1位开始向后遍历
            {
            //0~i-1位为有序，若第i位小于i-1位，继续寻位并插入，否则认为0~i位也是有序的，忽略此次循环，相当于continue
                if(array[i]<array[i-1]){
                    int temp=array[i];//保存第i位的值
                    int j = i - 1;
                        //从第i-1位向前遍历并移位，直至找到小于第i位值停止
                        while(j>=0 && temp<array[j])
                        {
                            array[j+1]=array[j];
                            j--;
                        }
                    array[j+1]=temp;//插入第i位的值
                }
            }
            System.out.println(Arrays.toString(array));
        }*/




        //希尔排序
        //希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
        // 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至 1 时，整个文件恰被分成一组，算法便终止
    /*    public static void shellSort(int[] array){
            System.out.println("排序之前：");
            for(int i=0;i<array.length;i++){
                System.out.print(array[i]+" ");
            }
            //希尔排序
            int gap = array.length;
            while (true) {
                gap /= 2;   //增量每次减半
                for (int i = 0; i < gap; i++) {
                    for (int j = i + gap; j < array.length; j += gap) {//这个循环里其实就是一个插入排序
                        int k = j - gap;
                        while (k >= 0 && array[k] > array[k+gap]) {
                            int temp = array[k];
                            array[k] = array[k+gap];
                            array[k + gap] = temp;
                            k -= gap;
                        }
                    }
                }
                if (gap == 1)
                    break;
            }

            System.out.println();
            System.out.println("排序之后：");
            for(int i=0;i<array.length;i++){
                System.out.print(array[i]+" ");
            }
        }*/



    //归并排序:使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为二路归并。
   /* public static int[] mergeSort(int[] nums, int l, int h) {
        if (l == h)
            return new int[] { nums[l] };

        int mid = l + (h - l) / 2;
        int[] leftArr = mergeSort(nums, l, mid); //左有序数组
        int[] rightArr = mergeSort(nums, mid + 1, h); //右有序数组
        int[] newNum = new int[leftArr.length + rightArr.length]; //新有序数组

        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newNum[m++] = leftArr[i] <= rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length)
            newNum[m++] = leftArr[i++];
        while (j < rightArr.length)
            newNum[m++] = rightArr[j++];
        return newNum;
    }*/


    //快速排序
    //首先设定一个分界值，通过该分界值将数组分成左右两部分。
    //将大于或等于分界值的数据集中到数组右边，小于分界值的数据集中到数组的左边。此时，左边部分中各元素都小于分界值，而右边部分中各元素都大于或等于分界值。
    //然后，左边和右边的数据可以独立排序。对于左侧的数组数据，又可以取一个分界值，将该部分数据分成左右两部分，同样在左边放置较小值，右边放置较大值。右侧的数组数据也可以做类似处理。
   /* public static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        // 核心算法部分：分别介绍 双边指针（交换法），双边指针（挖坑法），单边指针
        int pivotIndex = doublePointerSwap(arr, startIndex, endIndex);
        // int pivotIndex = doublePointerHole(arr, startIndex, endIndex);
        // int pivotIndex = singlePointer(arr, startIndex, endIndex);

        // 用分界值下标区分出左右区间，进行递归调用
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);

    }*/
    /**
     * 快速排序
     * 双边指针（交换法）
     * 思路：
     * 记录分界值 pivot，创建左右指针（记录下标）。
     * （分界值选择方式有：首元素，随机选取，三数取中法）
     *
     * 首先从右向左找出比pivot小的数据，
     * 然后从左向右找出比pivot大的数据，
     * 左右指针数据交换，进入下次循环。
     *
     * 结束循环后将当前指针数据与分界值互换，
     * 返回当前指针下标（即分界值下标）
     */
   /* private static int doublePointerSwap(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int leftPoint = startIndex;
        int rightPoint = endIndex;

        while (leftPoint < rightPoint) {
            // 从右向左找出比pivot小的数据
            while (leftPoint < rightPoint
                    && arr[rightPoint] > pivot) {
                rightPoint--;
            }
            // 从左向右找出比pivot大的数据
            while (leftPoint < rightPoint
                    && arr[leftPoint] <= pivot) {
                leftPoint++;
            }
            // 没有过界则交换
            if (leftPoint < rightPoint) {
                int temp = arr[leftPoint];
                arr[leftPoint] = arr[rightPoint];
                arr[rightPoint] = temp;
            }
        }
        // 最终将分界值与当前指针数据交换
        arr[startIndex] = arr[rightPoint];
        arr[rightPoint] = pivot;
        // 返回分界值所在下标
        return rightPoint;

    }*/
    public static void main(String[] args) {
        //冒泡排序
        int[] arr={8,5,45,888,13,57,1682,87,49};
        /*for (int i=0;i<=arr.length-1;i++){
               for (int j=0;j<= arr.length-1; j++) {
                   System.out.println("i="+i+",j="+j);
                   if(arr[i]<arr[j]){
                       int temp;
                       temp = arr[i];
                       arr[i]=arr[j];
                       arr[j]=temp;
                   }
               }
        }
        System.out.println(Arrays.toString(arr));*/
        //冒泡排序
        //bubbleSort(arr);
        //选择排序
        //selectionSort(arr);
        //插入排序
        //insertSort(arr);
        //希尔排序
        //shellSort(arr);
        //归并排序
        //int[] newNums = mergeSort(arr, 0, arr.length - 1);
        //System.out.println(Arrays.toString(newNums));
        //快速排序
        //quickSort(arr,0,arr.length-1);
        //堆排序
        System.out.println(Arrays.toString(heapSort(arr)));
    }
    //堆排序
    /**堆排序是指利用堆这种数据结构所设计的一种排序算法。
     * 堆是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点
     * 选择排序-堆排序
     * @param array 待排序数组
     * @return 已排序数组
     */
    public static int[] heapSort(int[] array) {
        //这里元素的索引是从0开始的,所以最后一个非叶子结点array.length/2 - 1
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);  //调整堆
        }

        // 上述逻辑，建堆结束
        // 下面，开始排序逻辑
        for (int j = array.length - 1; j > 0; j--) {
            // 元素交换,作用是去掉大顶堆
            // 把大顶堆的根元素，放到数组的最后；换句话说，就是每一次的堆调整之后，都会有一个元素到达自己的最终位置
            swap(array, 0, j);
            // 元素交换之后，毫无疑问，最后一个元素无需再考虑排序问题了。
            // 接下来我们需要排序的，就是已经去掉了部分元素的堆了，这也是为什么此方法放在循环里的原因
            // 而这里，实质上是自上而下，自左向右进行调整的
            adjustHeap(array, 0, j);
        }
        return array;
    }

    /**
     * 整个堆排序最关键的地方
     * @param array 待组堆
     * @param i 起始结点
     * @param length 堆的长度
     */
    public static void adjustHeap(int[] array, int i, int length) {
        // 先把当前元素取出来，因为当前元素可能要一直移动
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {  //2*i+1为左子树i的左子树(因为i是从0开始的),2*k+1为k的左子树
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && array[k] < array[k + 1]) {  //如果有右子树,并且右子树大于左子树
                k++;
            }
            //如果发现结点(左右子结点)大于根结点，则进行值的交换
            if (array[k] > temp) {
                swap(array, i, k);
                // 如果子节点更换了，那么，以子节点为根的子树会受到影响,所以，循环对子节点所在的树继续进行判断
                i  =  k;
            } else {  //不用交换，直接终止循环
                break;
            }
        }
    }

    /**
     * 交换元素
     * @param arr
     * @param a 元素的下标
     * @param b 元素的下标
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
