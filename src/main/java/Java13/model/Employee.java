package Java13.model;

import lombok.*;


    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor

    public class Employee {
        private Long id;
        private String firstName;
        private String lastName;
        private int age;
        private String email;
        private Long jobId;

        public Employee(String firstName, String lastName, int age, String email, Long jobId) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.email = email;
            this.jobId = jobId;
        }

        @Override
        public String toString() {
            return "\nEmployee{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    ", email='" + email + '\'' +
                    ", jobId=" + jobId +
                    '}';
        }
    }

