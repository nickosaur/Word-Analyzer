import React from 'react'
import { Link } from 'react-router-dom'

let endpoint = "http://localhost:8080/api/results";
export default class Results extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            result: []
        };
    }



    componentDidMount() {
        fetch(endpoint)
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
                <div className="container-fluid">
                    <div className="row justify-content-center">
                        <div className="col-lg-4">
                            <div className="card border-0 shadow">
                                <div className="card-body">
                                    <h3 className="card-title">Last 10 Analyzed Results</h3>
                                    {result.map((item, index) => {
                                        return (
                                            <div className="row">
                                                <div className="col-s">
                                                    {index + 1}. &nbsp;    File : <a href={"http://localhost:8080/api/download/" +
                                                        item.newFileName}>
                                                        {item.originalFileName}
                                                    </a>
                                                </div>
                                                <div className="col">
                                                    Stop Word Setting : {item.stopSetting.toString()}
                                                </div>
                                                <div className="col-s text-left">

                                                    <Link to={"/result/" + item.newFileName}

                                                        className="btn btn-outline-secondary btn-xs ">Details</Link>
                                                </div>

                                            </div>
                                        );
                                    })}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
    }
}