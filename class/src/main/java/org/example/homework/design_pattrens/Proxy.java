package org.example.homework.design_pattrens;

/**
 * @author yoveuio
 * @version 1.0
 * @className Proxy
 * @description 代理
 * @date 2020/12/9 14:51
 */
public class Proxy {

    /**
     * 客户端
     */
    public static void main(String[] args) {
        ILawsuit lawsuit = new XProgrammer();
        ILawsuit proxyLawyer = new ProxyLawyer(lawsuit);
        proxyLawyer.submit();
        proxyLawyer.defend();
        proxyLawyer.finish();
    }

    /**
     * 诉讼接口类
     */
    interface ILawsuit {
        /**
         * 提交材料
         */
        void submit();

        /**
         * 开始诉讼
         */
        void defend();

        /**
         * 诉讼完成
         */
        void finish();
    }

    /**
     * 真实诉讼人
     */
    static class XProgrammer implements ILawsuit {

        @Override
        public void submit() {
            System.out.println("提交材料");
        }

        @Override
        public void defend() {
            System.out.println("开始诉讼");
        }

        @Override
        public void finish() {
            System.out.println("诉讼完成，维护权益");
        }
    }

    /**
     * 诉讼人请的律师
     */
    static class ProxyLawyer implements ILawsuit {
        private volatile ILawsuit lawsuit;

        public ProxyLawyer(ILawsuit lawsuit) {
            if (this.lawsuit == null) {
                this.lawsuit = lawsuit;
            }
        }

        public void request(){
            submit();
            defend();
            finish();
        }

        @Override
        public void submit() {
            lawsuit.submit();
        }

        @Override
        public void defend() {
            lawsuit.defend();
        }

        @Override
        public void finish() {
            lawsuit.finish();
        }
    }
}
