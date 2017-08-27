class WebCLI
{
    constructor()
    {
        var self = this;
        self.history = []; //Command history
        self.cmdOffset = 0; //Reverse offset into history;
        self.createElements();
        self.wireEvents();
        self.showGreeting();
    }



    createElements() 
    {
        var self = this;
        var doc = document;

        //Create & store CLI elements
        self.ctrlEl = doc.createElement("div"); //CLI control (outer frame)
        self.outputEl = doc.createElement("div"); //Div holding console output
        self.inputEl = doc.createElement("input"); //Input control

        //Add classes
        self.ctrlEl.className = "webcli";
        self.outputEl.className = "webcli-output";
        self.inputEl.className = "webcli-input";

        //Add attribute
        self.inputEl.setAttribute("spellcheck", "false");

        //Assemble elements
        self.ctrlEl.appendChild(self.outputEl);
        self.ctrlEl.appendChild(self.inputEl);

        //Hide ctrl & add to DOM
        self.ctrlEl.style.display = "none";
        doc.body.appendChild(self.ctrlEl);
    }

    wireEvents() 
    {
        var self = this;

        self.keyDownHandler = function(e) { self.onKeyDown(e);}
        self.clickHandler = function(e) {self.onClick(e);}

        document.addEventListener('keydown', self.keyDownHandler);
        self.ctrlEl.addEventListener('click', self.clickHandler);
    }

    onClick(event) {
        this.focus();
    }

    onKeyDown(event)
    {
        var self = this;
        var ctrlStyle = self.ctrlEl.style;

        if(event.ctrlKey && event.keyCode == 223) {
            
            if(ctrlStyle.display == "none") 
            {
                ctrlStyle.display = "";
                self.focus();
            }
            else 
            {
                ctrlStyle.display = "none";
            }
        }

        if(self.inputEl === document.activeElement)
        {
            switch(event.keyCode) {
                case 13: //Enter
                    return self.runCmd();
                case 38: //Up
                    if((self.history.length + self.cmdOffset) > 0)
                    {
                        self.cmdOffset--;
                        self.inputEl.value = self.history[self.history.length + self.cmdOffset];
                        e.preventDefault();
                    }
                    break;
                case 40: //Down
                    if(self.cmdOffset < -1)
                    {
                        self.cmdOffset++;
                        self.inputEl.value = self.history[self.history.length + self.cmdOffset];
                        e.preventDefault();
                    }
                    break;
            }
        }
    }

    runCmd()
    {
        var self = this, txt = self.inputEl.value.trim();
        self.cmdOffset = 0;         //Reset history index
        self.inputEl.value = "";    //Clear input
        self.writeLine(txt, "cmd"); //Write cmd to output
        if(txt === "") {return;}    //If empty, stop processing
        self.history.push(txt);     //Add cmd to history

        //Client command:
        var tokens = txt.split(" "), cmd = tokens[0].toUpperCase();

        if(cmd === "CLEAR") {self.outputEl.innerHTML = ""; return;}
    }

    runServerCmd(cmdTxt) {
        var self = this;
        fetch("/api/webcli", {
            method: "post",
            headers: new Headers({"Content-Type": "application/json"}),
            body: JSON.stringify({cmdLine: cmdTxt})
        })
        .then(function(r) {return r.json(); })
        .then(function(result)
        {
            var output = result.output;
            var style = result.isError ? "error" : "ok";
            
            if(result.isHTML) 
            {
                self.writeHTML(output);
            } 
            else 
            {
                self.writeLine(output, style);
            }
        })
        .catch(function() { self.writeLine("Error sending request to server", "error"); });
    }

    scrollToBottom() 
    {
        this.ctrlEl.scrollTop = this.ctrlEl.scrollHeight;
    }

    newLine() 
    {
        this.outputEl.appendChild(document.createElement("br"));
        this.scrollToBottom();
    }

    writeLine(txt, cssSuffix) 
    {
        var span = document.createElement("span");
        cssSuffix = cssSuffix || "ok";
        span.className = "webcli-" + cssSuffix;
        span.innerText = txt;
        this.outputEl.appendChild(span);
        this.newLine();
    }

    writeHTML(markup) 
    {
        var div = document.createElement("div");
        div.innerHTML = markup;
        this.outputEL.appendChild(div);
        this.newLine();
    }

    showGreeting() 
    {
        this.writeLine("Web CLI [Version 0.0.1]", "cmd");
        this.newLine();
    }

    focus()
    {
        this.inputEl.focus();
    }
}