package org.atrocitywatch

import grails.transaction.Transactional

@Transactional
class NotificationService {

	def mailService
	def twilioSender

    def notify(User user,subject,msg) {
      if (user.email!=null) {
		  notifyByEmail(user,subject,msg)
	  }
	  if (user.phone!=null) {
		  notifyBySMS(user,subject,msg)
	  }
    }
	
	def notifyByEmail(User user,String subj,String msg) {
		mailService.sendMail {
			multipart false
			to user.email
			from  'attrocitywatch@theconnman.com'
			subject subj.toString()
			html msg
		}
	}

	def notifyBySMS(User user,String subject,String msg) {
		twilioSender.sendSMS(user.phone, msg);
	}
}
