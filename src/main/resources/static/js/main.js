
var urlApi = Vue.resource('/url{/link}');

// var router = new VueRouter({
//     routes: [
//         { path: '/a', redirect: '/b' }
//     ]
// });


Vue.component('reply-string', {
    props: ['info'],
    data: function() {
        return {
            response: ''
        }
    },
    template: '<div>{{ response }}</div>',
    watch: {
        info: function (newVal, oldVal) {
            this.response = newVal
        }
    }
});

Vue.component('long-to-short', {
    data:
        function() {
            return {
                text: '',
                response: '',
            }
        },
    template:
        '<div>' +
        '<div>Generate short link:</div>' +
        '<input type="text" placeholder="Your url" v-model="text" />' +
        '<input type="button" value="Generate" @click="generate" />' +
        '<reply-string :info="response" />' +
        '</div>',
    methods: {
        generate: function () {
            var longUrl = {text: this.text};

            console.log(longUrl);
            urlApi.update({}, longUrl).then(result =>
                result.json().then(data => {
                    console.log(data.message);
                    this.response = data.message
                })
            );
            this.text = ''
        }
    }
});

Vue.component('short-to-long', {
    data:
        function() {
            return {
                text: '',
                response: '',
            }
        },
    template:
        '<div>' +
            '<div>Get full url by short link:</div>' +
            '<input type="text" placeholder="Your url" v-model="text" />' +
            '<input type="button" value="Send" @click="getLong" />' +
            '<reply-string :info="response" />' +
        '</div>',
    methods: {
        getLong: function () {
            var shortUrl = {text: this.text};
            console.log(shortUrl);
            urlApi.save({}, shortUrl).then(result =>
                result.json().then(data => {
                    console.log(data.message);
                    this.response = data.message;
                })
            );
            this.text = ''
        }
    }
});

Vue.component('message-row', {
    props: ['row'],
    template: '<div>{{ row.text }}</div>'
});



var shortener = new Vue({
    el: '#shortener',
    template:
        '<div>' +
            '<long-to-short />' +
            '<short-to-long />' +
        '</div>'
});