package university2

class Enrollment {

    Integer grade
    Date enrolledAt = new Date()

    static belongsTo = [
        student: Student,
        course: Course
    ]

    static constraints = {
        grade nullable: true
    }
}