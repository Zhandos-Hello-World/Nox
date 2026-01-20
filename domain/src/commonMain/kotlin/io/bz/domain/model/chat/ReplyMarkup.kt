package io.bz.domain.model.chat

sealed interface ReplyMarkup {

    data class RemoveKeyboard(
        val isPersonal: Boolean
    ) : ReplyMarkup

    data class ForceReply(
        val isPersonal: Boolean,
        val inputPlaceholder: String?
    ) : ReplyMarkup

    data class ShowKeyboard(
        val rows: List<List<KeyboardButton>>,
        val isPersistent: Boolean,
        val resizeKeyboard: Boolean,
        val oneTime: Boolean,
        val isPersonal: Boolean,
        val inputPlaceholder: String?
    ) : ReplyMarkup {

        data class KeyboardButton(
            val text: String,
            val type: KeyboardButtonType
        ) {

            sealed interface KeyboardButtonType {

                object Text : KeyboardButtonType

                object RequestPhoneNumber : KeyboardButtonType

                object RequestLocation : KeyboardButtonType

                data class RequestPoll(
                    val forceRegular: Boolean,
                    val forceQuiz: Boolean
                ) : KeyboardButtonType

                data class RequestUsers(
                    val userIsBot: Boolean?,
                    val userIsPremium: Boolean?,
                    val maxQuantity: Int
                ) : KeyboardButtonType

                data class RequestChat(
                    val isChannel: Boolean,
                    val isForum: Boolean?,
                    val hasUsername: Boolean?,
                    val isCreated: Boolean?,
//                    val userAdministratorRights: ChatAdministratorRights?,
//                    val botAdministratorRights: ChatAdministratorRights?
                ) : KeyboardButtonType

                data class WebApp(
                    val url: String
                ) : KeyboardButtonType
            }

        }
    }

    data class InlineKeyboard(
        val rows: List<List<InlineKeyboardButton>>
    ) : ReplyMarkup
}