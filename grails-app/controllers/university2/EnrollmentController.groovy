package university2

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EnrollmentController {

    EnrollmentService enrollmentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond enrollmentService.list(params), model:[enrollmentCount: enrollmentService.count()]
    }

    def show(Long id) {
        respond enrollmentService.get(id)
    }

    def create() {
        respond new Enrollment(params)
    }

    // دالة الحفظ مع منطق منع التكرار
    def save() {
        def student = Student.get(params.student.id)
        def course = Course.get(params.course.id)

        // منع التكرار
        def exists = Enrollment.findByStudentAndCourse(student, course)
        if (exists) {
            flash.message = "الطالب مسجل مسبقاً في هذا الكورس"
            redirect(action: "index")
            return
        }

        def enrollment = new Enrollment(
            student: student,
            course: course,
            grade: params.grade ? params.int('grade') : null
        )

        if (!enrollment.save(flush: true)) {
            respond enrollment.errors, view:'create'
            return
        }

        flash.message = "تم تسجيل الطالب بنجاح"
        redirect(action: "index")
    }

    def edit(Long id) {
        respond enrollmentService.get(id)
    }

    def update(Enrollment enrollment) {
        if (enrollment == null) {
            notFound()
            return
        }

        try {
            enrollmentService.save(enrollment)
        } catch (ValidationException e) {
            respond enrollment.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'enrollment.label', default: 'Enrollment'), enrollment.id])
                redirect enrollment
            }
            '*'{ respond enrollment, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        enrollmentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'enrollment.label', default: 'Enrollment'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'enrollment.label', default: 'Enrollment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
