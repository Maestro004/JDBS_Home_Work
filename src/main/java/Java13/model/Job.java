package Java13.model;


import lombok.*;

@Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public class Job {
        private Long id;
        private String position;     //("Mentor","Management","Instructor") ушундай маанилер берилсин
        private String profession;    //("Java","JavaScript")
        private String description;  //("Backend developer","Fronted developer")
        private int experience;  // (1,2,3........) опыт работы
}
