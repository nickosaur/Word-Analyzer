import React from 'react'
import { Link } from 'react-router-dom'

let endpoint = "http://localhost:8080/api/";
export default class Result extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            result: []
        };
    }



    componentDidMount() {
        var res = this.props.match.params.result;
        res = res.substring(0, res.length - 4);
        fetch(endpoint + res)
            .then(res => res.json())
            .then(
                (data) => {
                    this.setState({
                        isLoaded: true,
                        result: data
                    });
                },
                (error) => {
                    console.log(error);
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    render() {
        const { error, isLoaded, result } = this.state;
        if (error) {
            return <div>Error: {error.message}</div>;
        } else if (!isLoaded) {
            return <div>Loading...</div>;
        } else {
            return (
                <div className="container">
                    <div className="row justify-content-center p-5">
                        <div className="col-lg-4 col-md-4 col-sm-8">
                            <div className="card border-0 shadow">
                                <div className="card-body">
                                    <h3 className="card-title">Analyzed Result</h3>
                                    <div>
                                        File : <a href={"http://localhost:8080/api/download/" +
                                            result.newFileName}>
                                            {result.originalFileName}
                                        </a>
                                    </div>
                                    <div>
                                        Stop Word Setting : {result.stopSetting.toString()}
                                    </div>
                                    <div>
                                        {result.wordList.map((w) =>
                                            <div className="row justify-content-center" key={w.word}>
                                                <div className="col-sm text-left">
                                                    Word:   {w.word}
                                                </div>
                                                <div className="col-sm text-left">
                                                    Count: {w.count}
                                                </div>
                                            </div>)}
                                    </div>
                                    <div>&nbsp;</div>
                                    <div className="text-center">
                                        <Link to="/upload" className="btn btn-outline-primary mr-2 ">Upload Again</Link>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>);
        }
    }
}