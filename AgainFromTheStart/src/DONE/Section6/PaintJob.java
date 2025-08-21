package DONE.Section6;

public class PaintJob {

    public static int getBucketCount (double width, double height, double areaPerBucket, int extraBuckets){

        if (width <= 0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0){
            return -1;
        } else {
            double totalArea = width * height;
            double areaPainted = (extraBuckets * areaPerBucket);
            int requiredBuckets = 0;
            while (areaPainted <= totalArea){
                requiredBuckets++;
                areaPainted += areaPerBucket;
            }
            return requiredBuckets;
        }
    }

    public static int getBucketCount (double width, double height, double areaPerBucket){

        return getBucketCount(width, height,areaPerBucket,0);
//        if (width < 0 || height < 0 || areaPerBucket < 0){
//            return -1;
//        } else {
//            double totalArea = width * height;
//            int requiredBuckets = 1; //always need at least 1 bucket
//            double paintPerBucket = areaPerBucket;
//            while (paintPerBucket <= totalArea ){
//                paintPerBucket += areaPerBucket;
//                requiredBuckets++;
//            }
//            return requiredBuckets;
//        }
    }

    public static int getBucketCount (double area, double areaPerBucket){

        return getBucketCount (area, 1, areaPerBucket, 0);
//        if (area < 0 || areaPerBucket < 0){
//            return -1;
//        } else {
//            int requiredBuckets = 1;
//            double areaToPaint = areaPerBucket;
//            while (areaToPaint <= area){
//                areaToPaint += areaPerBucket;
//                requiredBuckets++;
//            }
//            return requiredBuckets;
//        }
    }
}
