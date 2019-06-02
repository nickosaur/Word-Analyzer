import React from 'react'
import { Redirect } from 'react-router-dom'

var endpoint = 'http://localhost:8080/api/analyze'

export default class Upload extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            result: []
        };

        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(e) {
        e.preventDefault();
        let body = new FormData();

        let data = Array.prototype.filter.call(e.target.elements,
            (input) => {
                if (input.nodeName === 'BUTTON') return false;
                return true;
            });

        Array.prototype.map.call(data, (input) => {
            input.id !== 'file' ? body.append(input.id, input.value)
                : body.append('file', input.files[0]);

        });
        fetch(endpoint, {
            method: 'POST',
            body: body,
        }).then(res => res.json())
            .then(response => {
                console.log(response);
                this.setState({
                    isLoaded: true,
                    result: response
                });
            })
            .catch(error => console.error('Error:', error));
    }

    render() {
        if (this.state.isLoaded) {

            var result = this.state.result;
            var redirectURL = '/result/' + (result.newFileName);
            return (
                <Redirect to={redirectURL} />
            );
        } else {
            return (
                <div className="container">
                    <div className="row justify-content-center p-5">
                        <div className="col-lg-4 col-md-4 col-sm-8">
                            <div className="card border-0 shadow">
                                <div className="card-body">
                                    <h3 className="card-title text-center">Upload File</h3>
                                    <form onSubmit={this.onSubmit}>
                                        <fieldset>
                                            <div className="form-group">
                                                <input
                                                    type="file"
                                                    id="file"
                                                    accept=".txt"
                                                    required
                                                />
                                            </div>
                                            <div className="form-group">
                                                <label> Exclude Stop Word? &nbsp; </label>
                                                <input
                                                    type="checkbox"
                                                    id="stopWords"
                                                />
                                            </div>
                                            <button type="submit" className="btn btn-primary btn-sm btn-block">
                                                Submit
                                            </button>
                                        </fieldset>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
    }
}