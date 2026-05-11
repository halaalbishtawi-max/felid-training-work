package university2

import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional

class EnrollmentService {

    def enroll(Long studentId, Long courseId) {

        log.info("Enroll method started")

        try {

            def student = Student.get(studentId)
            def course = Course.get(courseId)

            if (!student || !course) {
                return null
            }

            // منع التكرار
            def exists = Enrollment.findByStudentAndCourse(student, course)

            if (exists) {
                return null
            }

            def enrollment = new Enrollment(
                    student: student,
                    course: course
            )

            enrollment.save(flush: true)

            return enrollment

        } catch (Exception e) {

            log.error("Error in enroll()", e)

            transactionStatus.setRollbackOnly()

            return null
        }
    }

    def unenroll(Long enrollmentId) {

        log.info("Unenroll method started")

        try {

            def enrollment = Enrollment.get(enrollmentId)

            if (enrollment) {
                enrollment.delete(flush: true)
            }

        } catch (Exception e) {

            log.error("Error in unenroll()", e)

            transactionStatus.setRollbackOnly()
        }
    }

    double calculateGpa(Long studentId) {

        log.info("calculateGpa started")

        try {

            def student = Student.get(studentId)

            if (!student || !student.enrollments) {
                return 0
            }

            def grades = student.enrollments
                    .findAll { it.grade != null }
                    .collect { it.grade }

            if (grades.size() == 0) {
                return 0
            }

            return grades.sum() / grades.size()

        } catch (Exception e) {

            log.error("Error in calculateGpa()", e)

            return 0
        }
    }
}