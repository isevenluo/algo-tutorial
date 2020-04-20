package org.luoxiaohei.java.stack;

/**
 * @description: 使用前后栈实现浏览器的前进后退。
 * @outhor: luoxiaohei
 * @create: 2020-04-20 21:08
 */
public class SampleBrowser {

    public static void main(String[] args) {
        SampleBrowser browser = new SampleBrowser();
        browser.open("http://www.baidu.com");
        browser.open("http://news.baidu.com/");
        browser.open("http://news.baidu.com/ent");
        browser.goBack();
        browser.goBack();
        browser.goForward();
        browser.open("http://www.qq.com");
        browser.goForward();
        browser.goBack();
        browser.goForward();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.checkCurrentPage();
    }


    private String currentPage;
    private LinkedListBaseStack backStack;
    private LinkedListBaseStack forwardStack;

    public SampleBrowser() {
        this.backStack = new LinkedListBaseStack();
        this.forwardStack = new LinkedListBaseStack();
    }

    public void open(String url) {
        if (this.currentPage != null) {
            this.backStack.push(currentPage);
            // 新打开页面，无法前进，所以清空
            this.forwardStack.clear();
        }
        showUrl(url,"open");
    }

    public boolean canGoBack() {
        return this.backStack.size > 0;
    }

    public boolean canGoForward() {
        return this.forwardStack.size > 0;
    }

    public String goBack() {
        if (canGoBack()) {
            this.forwardStack.push(this.currentPage);
            String backUrl = this.backStack.pop();
            showUrl(backUrl,"Back");
            return backUrl;
        }
        System.out.println("* Cannot go back, no pages behind.");
        return null;
    }

    public String goForward() {
        if (canGoForward()) {
            this.backStack.push(this.currentPage);
            String url = this.forwardStack.pop();
            showUrl(url,"Forward");
            return url;
        }
        System.out.println("* Cannot go forward, no pages ahead.");
        return null;
    }


    public void showUrl(String url, String prefix) {
        this.currentPage = url;
        System.out.println(prefix + " page == " + url);
    }

    public void checkCurrentPage() {
        System.out.println("Current page is: " + this.currentPage);
    }

    public static class LinkedListBaseStack {
        private int size;
        private Node top;

        static Node createNode (String data,Node next) {
            return new Node(data,next);
        }

        public void clear() {
            this.top = null;
            size = 0;
        }


        public void push(String data) {
            Node node = createNode(data,this.top);
            this.top = node;
            this.size++;
        }

        public String pop() {
            Node popNode = this.top;
            if (popNode == null) {
                System.out.println("Stack is empty");
                return null;
            }
            this.top = popNode.next;
            if (this.size > 0) {
                this.size--;
            }
            return popNode.data;
        }

        public String getTopData() {
            if (this.top != null) {
                return null;
            }
            return this.top.data;
        }

        public int size() {
            return this.size;
        }

        public void print() {
            System.out.println("Print stack:");
            Node currentNode = this.top;
            while (currentNode != null) {
                String data = currentNode.getData();
                System.out.print(data + "\t");
                currentNode = currentNode.next;
            }
            System.out.println();
        }

    }

    public static class Node {
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(String data) {
            this(data,null);
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
