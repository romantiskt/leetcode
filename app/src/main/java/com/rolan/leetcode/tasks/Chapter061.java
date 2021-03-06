package com.rolan.leetcode.tasks;

import com.rolan.leetcode.IEngine;

/**
 * Created by wangyang on 2019-07-05.18:36
 */
public class Chapter061 implements IEngine {
    @Override
    public void doMath() {
        ListNode randomLink = createRandomLink(10, 100);
        String input=linkToStr(randomLink);
        ListNode result = rotateRight(randomLink, 2);
        showResultDialg(getQuestion(),input,linkToStr(result));
    }

    public ListNode rotateRight(ListNode head, int k) {
        if(head==null||head.next==null)return head;
        ListNode tail=head;
        int len=1;
        while (tail.next!=null){
            tail=tail.next;
            len++;
        }
        tail.next=head;//将链表连接成环闭合，然后旋转就意味着重新找到新的头结点然后断开

        ListNode newNode=head;
        for(int i=0;i<(len-k%len);i++){
            ListNode  node=newNode.next;
            if((i==(len-k%len-1))){
                newNode.next=null;
            }
            newNode=node;

        }
        ListNode result=newNode;
        return result;
    }

    /**
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * 示例 2:
     *
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @return
     */
    @Override
    public String getQuestion() {
        return "旋转链表";
    }
}
