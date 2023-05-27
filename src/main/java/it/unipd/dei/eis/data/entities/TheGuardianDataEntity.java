package it.unipd.dei.eis.data.entities;

import java.util.ArrayList;
import java.util.Date;

/**
 * TheGuardianDataEntity is the data entity for The Guardian records.
 * It contains the data structure of the JSON response.
 * It is used by the Gson library to parse the JSON response.
 */
public class TheGuardianDataEntity implements IDataEntity {

    /**
     * The Response class contains the data structure of the JSON response.
     * It is used by the Gson library to parse the JSON response.
     */
    public final Response response;

    /**
     * TheGuardianDataEntity constructor.
     * It is used by the Gson library to parse the JSON response.
     *
     * @param response the JSON response
     */
    private TheGuardianDataEntity(Response response) {
        this.response = response;
    }

    /**
     * The Response class contains the data structure of the JSON response.
     * It is used by the Gson library to parse the JSON response.
     */
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

        /**
         * The Response constructor.
         * It is used by the Gson library to parse the JSON response.
         *
         * @param status      the status field
         * @param userTier    the userTier field
         * @param total       the total field
         * @param startIndex  the startIndex field
         * @param pageSize    the pageSize field
         * @param currentPage the currentPage field
         * @param pages       the pages field
         * @param orderBy     the orderBy field
         * @param results     the results field
         */
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

        /**
         * The Result class contains the data structure of the JSON response.
         * It is used by the Gson library to parse the JSON response.
         */
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
