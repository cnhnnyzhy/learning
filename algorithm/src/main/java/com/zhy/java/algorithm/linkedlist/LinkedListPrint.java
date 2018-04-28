package com.zhy.java.algorithm.linkedlist;

import java.util.Stack;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/28
 */
public class LinkedListPrint {
    public static void main(String[] args){
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, null);
        ListNode node3 = new ListNode(3, null);
        ListNode node4 = new ListNode(4, null);
        ListNode node5 = new ListNode(5, null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        //print(node1);
        print2(node1);
    }
    /**
     * @Description: 利用栈来输出
     * @MethodName: print
     * @param header
     * @return: void
     * @Author: yang.zhang3
     * @Date: 17:24 2018/4/28
     */
    private static void print(ListNode header){
        Stack<ListNode> stack = new Stack<ListNode>();
        while (header != null){
            stack.push(header);
            header = header.next;
        }
        while (!stack.empty()){
            System.out.print(stack.pop().data + " ");
        }
    }
    /**
     * @Description: 使用递归的方式输出，受函数调用层级深度的限制，不适用于链表较大的情况。
     * @MethodName: print2
     * @param header
     * @return: void
     * @Author: yang.zhang3
     * @Date: 17:28 2018/4/28
     */
    private static void print2(ListNode header){
        if (header != null){
            print2(header.next);
            System.out.print(header.data + " ");
        }
    }
}
