package com.example.terminal.response;


public class ContractResponse {
        private Long id;
        private String contractNum;
        private String contractDate;
        private String company;
        private String inn;
        private String address;
        private String clientName;
        private String clientContacts;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getContractNum() {
            return contractNum;
        }

        public void setContractNum(String contractNum) {
            this.contractNum = contractNum;
        }

        public String getContractDate() {
            return contractDate;
        }

        public void setContractDate(String contractDate) {
            this.contractDate = contractDate;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getInn() {
            return inn;
        }

        public void setInn(String inn) {
            this.inn = inn;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String getClientContacts() {
            return clientContacts;
        }

        public void setClientContacts(String clientContacts) {
            this.clientContacts = clientContacts;
        }
}
