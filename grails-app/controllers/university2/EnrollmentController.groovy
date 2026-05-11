package university2

class EnrollmentController {

    EnrollmentService enrollmentService

    def index() {

        [enrollmentList: Enrollment.list()]
    }

    def create() {
    }

    def save() {

        def enrollment = enrollmentService.enroll(
                params.long('student.id'),
                params.long('course.id')
        )

        if (!enrollment) {

            flash.message = "Enrollment failed or duplicate"

            redirect(action: "index")

            return
        }

        flash.message = "Enrollment created"

        redirect(action: "index")
    }

    def delete(Long id) {

        enrollmentService.unenroll(id)

        flash.message = "Enrollment deleted"

        redirect(action: "index")
    }
}