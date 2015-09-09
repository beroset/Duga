package net.zomis.duga.tasks.qscan

import net.zomis.duga.ChatBot
import net.zomis.duga.StackAPI
import net.zomis.duga.chat.WebhookParameters

import java.time.Instant

class AnswerInvalidationCheck {

    static void perform(def result, Instant lastCheck, StackAPI stackExchangeAPI, ChatBot dugaBot, WebhookParameters params) {
        println 'Answer invalidation check'
        List questions = result.items
        questions.each {
//            def created = it.creation_date
//            def activity = it.creation_date
            def edited = it.last_edit_date
            def link = it.link
            if (edited >= lastCheck.epochSecond && it.answer_count > 0) {
                println 'edited: ' + it.question_id
                int id = it.question_id
                def edits = stackExchangeAPI.apiCall(editCall(id), 'codereview', '!9YdnS7lAD')
                dugaBot.postDebug("Edits fetched for $id: ${edits.items.size()}. quota remaining $edits.quota_remaining")
                def answerInvalidation = codeChanged(edits, lastCheck)
                if (answerInvalidation) {
                    dugaBot.postChat(params, ['*possible answer invalidation:* ' + link])
                }
            }
        }
    }

    static boolean codeChanged(def edits, Instant lastCheck) {
        boolean answerInvalidation = false
        edits.items.each {
            if (!it.last_body) {
                return;
            }
            if (it.creation_date < lastCheck.epochSecond) {
                return;
            }
            if (it.is_rollback) {
                return;
            }
            String code = stripNonCode(it.body)
            String codeBefore = stripNonCode(it.last_body)
            if (!code.equals(codeBefore)) {
                answerInvalidation = true
            }
        }
        return answerInvalidation
    }

    static String editCall(int id) {
        "posts/$id/revisions"
    }

    static String stripNonCode(String post) {
        int keepCount = 0
        int index = post.indexOf('<code>')
        while (index >= 0) {
            int endIndex = post.indexOf('</code>')
            assert endIndex >= 0
            String before = post.substring(0, keepCount)
            String code = post.substring(index + '<code>'.length(), endIndex)
            String after = post.substring(endIndex + '</code>'.length())
            if (code.contains('\\n') || code.contains('\n')) {
                post = before + code + after
                keepCount += code.length()
            } else {
                post = before + after
            }
            index = post.indexOf('<code>')
        }
        return post.substring(0, keepCount)
    }

}
