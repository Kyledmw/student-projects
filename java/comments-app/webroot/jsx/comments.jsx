var eb;
var initializeEventbus = function(component) {
    var url = window.location.protocol + "//" +  window.location.hostname + ':' + window.location.port + "/eventbus";
    eb = new vertx.EventBus(url);

    eb.onopen = function() {
        component.fetchComments();
        eb.registerHandler("comments.all_comments", function(event) {
        	var comments = JSON.parse(event);
            component.renderComments(comments);
        });
    }
};

var CommentBox = React.createClass({
    fetchComments: function() {
        var component = this;
        eb.send("comments.get_comments", {}, function(result) {
            result = JSON.parse(result);
            component.renderComments(result);
        });
    },
    renderComments: function(comments) {
        this.setState({ data: comments });
    },
    getInitialState: function() {
        return { data: [] };
    },
    componentDidMount: function() {
        var component = this;
        initializeEventbus(component);
    },
    render: function() {
        return (
            <div className="commentBox">
                <h1>Comments</h1>
                <CommentList data={this.state.data} />
                <CommentForm/>
            </div>
        );
    }
});

var CommentList = React.createClass({
    render: function() {
        var commentNodes = this.props.data.map(function(comment) {
            return (
                <Comment author={comment.author} message={comment.message}>
                </Comment>
            );
        });
        return (
            <div className="commentList">
                {commentNodes}
            </div>
        );
    }
});

var CommentForm = React.createClass({
    handleSubmit: function(event) {
        event.preventDefault();
        var author = this.refs.author.getDOMNode().value.trim();
        var text = this.refs.text.getDOMNode().value.trim();
        if (!text || !author) {
            return false;
        };
        eb.send("comments.create_comment", { author: author, message: text });
        this.refs.author.getDOMNode().value = '';
        this.refs.text.getDOMNode().value = '';
        return false;
    },
    render: function() {
        return (
            <form className="commentForm" onSubmit={this.handleSubmit}>
                <input type="text" placeholder="Your Name" ref="author" />
                <input type="text" placeholder="Say something..." ref="text" />
                <input type="submit" value="Post" />
            </form>
        );
    }
});

var Comment = React.createClass({
    render: function() {
        return (
            <div className="comment">
                <h2 className="commentAuthor">
                    {this.props.author}
                </h2>
                <p>{this.props.message}</p>
            </div>
        );
    }
});

React.render(
    <CommentBox />,
    document.getElementById('render-target')
);
