package com.zhy.java.thread.book1.chapter07.eg7_6;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class ForkJoinTaskDemo {
    public static void main(String[] args){
        Integer count = new ForkJoinPool().invoke(new CountingTask(Paths.get("E:\\")));
        System.out.println("文件数量为：" + count);
    }
}

class CountingTask extends RecursiveTask<Integer>{
    private Path dir;
    public CountingTask(Path dir){
        this.dir = dir;
    }
    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Integer compute() {
        int count = 0;
        List<CountingTask> subTasks = new ArrayList<>();
        try {
            DirectoryStream<Path> ds = Files.newDirectoryStream(dir);
            for (Path subPath : ds){
                if(Files.isDirectory(subPath, LinkOption.NOFOLLOW_LINKS)){
                    subTasks.add(new CountingTask(subPath));
                }else{
                    count++;
                }
            }
            if(!subTasks.isEmpty()){
                for (CountingTask subTask : invokeAll(subTasks)){
                    count += subTask.join();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return count;
    }
}
