package com.example.milestoneproject

object setData {

    fun getAndroidQuestion(): ArrayList<QuestionData> {
        var question: ArrayList<QuestionData> = arrayListOf()
        var q1 = QuestionData(
            "__ is a way to restrict direct access to some of an object’s fields.",
            1,
            "ViewModel",
            "Score",
            "LiveData",
            "Encapsulation",
            4
        )

        var q2 = QuestionData(
            "All classes in kotlin classes are by default?",
            1,
            "Final",
            "Public",
            "Private",
            "Abstract",
            1
        )

        var q3 = QuestionData(
            "What is the correct way to initialize a nullable variable?",
            1,
            "val name = null",
            "var name:String",
            "val name: String",
            "val name:String ?= null",
            4
        )

        var q4 = QuestionData(
            "Which of the following option is used to handle null exceptions in kotlin?",
            1,
            "Range",
            "Elvis Operator",
            "Lambda Function",
            "Sealed Class",
            2
        )

        var q5 = QuestionData(
            "Kotlin is developed by?",
            1,
            "Google",
            "JetBrains",
            "Microsoft",
            "Adobe",
            2
        )

        question.add(q1)
        question.add(q2)
        question.add(q3)
        question.add(q4)
        question.add(q5)
        return question
    }

    fun getHtmlQuestion(): ArrayList<QuestionData> {
        var question: ArrayList<QuestionData> = arrayListOf()
        var q1 = QuestionData(
            "Who is the father of HTML?",
            1,
            "Rasmus Lerdorf",
            "Tim Berners-Lee",
            "Brendan Eich",
            "Sergey Brin",
            2
        )

        var q2 = QuestionData(
            "HTML is a set of markup ___.",
            1,
            "Tags",
            "Sets",
            "Attributes",
            "None of the above",
            1
        )

        var q3 = QuestionData(
            "HTML program can be read and rendered by ___.",
            1,
            "Compiler",
            "Server",
            "Web Browser",
            "Interpreter",
            3
        )

        var q4 = QuestionData(
            "Which is the first web browser ?",
            1,
            "Nexus",
            "Internet Explorer",
            "Mosaic",
            "Netscape Navigator",
            1
        )

        var q5 = QuestionData(
            "Which is used to open the document in new window ?",
            1,
            "<a target='_top'>Link</a>",
            "<a target='_blank''>Link</a>",
            " <a target='_new''>Link</a>",
            "<a target='_parent''>Link</a>",
            2
        )

        question.add(q1)
        question.add(q2)
        question.add(q3)
        question.add(q4)
        question.add(q5)
        return question
    }

    fun getCssQuestion(): ArrayList<QuestionData> {
        var question: ArrayList<QuestionData> = arrayListOf()
        var q1 = QuestionData(
            "Box-Shadow is a property that allows developers to apply a ___.",
            1,
            "Border",
            "Drop Shadow",
            "Background",
            "Rounded Corner",
            2
        )

        var q2 = QuestionData(
            "What is the correct CSS syntax for making all the <span> elements bold?",
            1,
            "span {text-size: bold}",
            "span {font-weight: bold}",
            "<span style='font-size: bold'>",
            "<span style='text-size: bold'>",
            2
        )

        var q3 = QuestionData(
            "What property is used to change the text color of an element?",
            1,
            "fontcolor:",
            "textcolor:",
            "color:",
            "font-color:",
            3
        )

        var q4 = QuestionData(
            "The # symbol specifies that the selector is?",
            1,
            "Class",
            "Tag",
            "First",
            "Id",
            4
        )

        var q5 = QuestionData(
            "How do you make a list not display bullet points?",
            1,
            "list-style-type: no-bullet",
            "list: none",
            "list-style-type: none",
            "bulletpoints: none",
            3
        )

        question.add(q1)
        question.add(q2)
        question.add(q3)
        question.add(q4)
        question.add(q5)
        return question
    }

    fun getJsQuestion(): ArrayList<QuestionData> {
        var question: ArrayList<QuestionData> = arrayListOf()
        var q1 = QuestionData(
            "To insert a JavaScript into an HTML page, which tag is used?",
            1,
            "<js>",
            "<javascript>",
            "<script>",
            "<scripts>",
            3
        )

        var q2 = QuestionData(
            "What is the alternate name for Java script?",
            1,
            "Limescript",
            "ECMScript",
            "ECMAScript",
            "Both A and  C",
            3
        )

        var q3 = QuestionData(
            "JavaScript is a ___ -side programming language.",
            1,
            "Client",
            "Server",
            "Both Client and Sever",
            "None",
            3
        )

        var q4 = QuestionData(
            "What is the correct JavaScript syntax to print “hello” in the console?",
            1,
            "console.print(“hello”);",
            " log.console(“hello”);",
            "console.log(“hello”);",
            "print(“hello”);",
            3
        )

        var q5 = QuestionData(
            "Which of the following statements will show a message as well as ask for user input in a popup?",
            1,
            "alert()",
            "prompt()",
            "confirm()",
            "message()",
            2
        )

        question.add(q1)
        question.add(q2)
        question.add(q3)
        question.add(q4)
        question.add(q5)
        return question
    }

}