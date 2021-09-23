package cn.smart.common.api.result;

public class ApiPageResult extends ApiResult{

    private Page page;

    public ApiPageResult(String msg) {
        super(msg);
    }

    public ApiPageResult(Object object) {
        super(object);
    }

    public ApiPageResult(Object data, int currentPage, int pageSize, int total) {
        super(data);
        this.page = new Page(currentPage, pageSize, total);
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    class Page {

        public Page(int currentPage, int pageSize, int total) {
            this.currentPage = currentPage;
            this.pageSize = pageSize;
            this.total = total;
        }

        private int currentPage;

        private int pageSize;

        private int total;



        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

}
