package it.unipd.dei.eis.data.entities;

import java.util.ArrayList;
import java.util.Date;

public class TheGuardianIDataEntity implements IDataEntity {
    public final Response response;

    private TheGuardianIDataEntity(Response response) {
        this.response = response;
    }

    public static class Response {
        public final String status;
        public final String userTier;
        public final int total;
        public final int startIndex;
        public final int pageSize;
        public final int currentPage;
        public final int pages;
        public final String orderBy;
        public final ArrayList<Result> results;

        private Response(String status, String userTier, int total, int startIndex, int pageSize, int currentPage, int pages, String orderBy, ArrayList<Result> results) {
            this.status = status;
            this.userTier = userTier;
            this.total = total;
            this.startIndex = startIndex;
            this.pageSize = pageSize;
            this.currentPage = currentPage;
            this.pages = pages;
            this.orderBy = orderBy;
            this.results = results;
        }

        public static class Result {
            public final String id;
            public final String type;
            public final String sectionId;
            public final String sectionName;
            public final Date webPublicationDate;
            public final String webTitle;
            public final String webUrl;
            public final String apiUrl;
            public final Fields fields;
            public final boolean isHosted;
            public final String pillarId;
            public final String pillarName;

            private Result(String id, String type, String sectionId, String sectionName, Date webPublicationDate, String webTitle, String webUrl, String apiUrl, Fields fields, boolean isHosted, String pillarId, String pillarName) {
                this.id = id;
                this.type = type;
                this.sectionId = sectionId;
                this.sectionName = sectionName;
                this.webPublicationDate = webPublicationDate;
                this.webTitle = webTitle;
                this.webUrl = webUrl;
                this.apiUrl = apiUrl;
                this.fields = fields;
                this.isHosted = isHosted;
                this.pillarId = pillarId;
                this.pillarName = pillarName;
            }

            public static class Fields {
                public final String bodyText;

                private Fields(String bodyText) {
                    this.bodyText = bodyText;
                }
            }
        }
    }
}
