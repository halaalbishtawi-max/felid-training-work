package university2

class Course {

    String title
    String code
    Integer creditHours

    static constraints = {
        code unique: true
        creditHours range: 1..6
    }
}